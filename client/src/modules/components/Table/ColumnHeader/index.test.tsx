/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. Licensed under a commercial license.
 * You may not use this file except in compliance with the commercial license.
 */

import {render, screen} from '@testing-library/react';
import {ThemeProvider} from 'modules/theme/ThemeProvider';
import {MemoryRouter} from 'react-router-dom';
import {ColumnHeader} from './index';

const Wrapper: React.FC = ({children}) => {
  return (
    <ThemeProvider>
      <MemoryRouter>{children}</MemoryRouter>
    </ThemeProvider>
  );
};

describe('ColumnHeader', () => {
  it('should render a button if the column is sortable', () => {
    render(<ColumnHeader label="Start Time" sortKey="startDate" />, {
      wrapper: Wrapper,
    });
    expect(screen.getByText('Start Time')).toBeInTheDocument();
    expect(
      screen.getByRole('button', {name: 'Sort by Start Time'})
    ).toBeInTheDocument();
  });

  it('should only render the text if the column is not sortable', () => {
    render(<ColumnHeader label="Start Time" />, {
      wrapper: Wrapper,
    });
    expect(screen.getByText('Start Time')).toBeInTheDocument();
    expect(
      screen.queryByRole('button', {name: 'Sort by Start Time'})
    ).not.toBeInTheDocument();
  });
});
