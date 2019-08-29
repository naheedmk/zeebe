/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. Licensed under a commercial license.
 * You may not use this file except in compliance with the commercial license.
 */

import {createInstance, createOperation} from 'modules/testUtils';
import {EXPAND_STATE, DEFAULT_SORTING, DEFAULT_FILTER} from 'modules/constants';

// mock props
const filterCount = 27;
const onFirstElementChange = jest.fn();
const INSTANCE = createInstance({
  id: '1',
  operations: [createOperation({state: 'FAILED'})],
  hasActiveOperation: false
});
export const ACTIVE_INSTANCE = createInstance({
  id: '2',
  operations: [createOperation({state: 'SENT'})],
  hasActiveOperation: true
});

export const mockProps = {
  expandState: EXPAND_STATE.DEFAULT,
  filter: DEFAULT_FILTER,
  filterCount: filterCount,
  instancesLoaded: false,
  instances: [],
  sorting: DEFAULT_SORTING,
  onSort: jest.fn(),
  firstElement: 0,
  onFirstElementChange: onFirstElementChange,
  onWorkflowInstancesRefresh: jest.fn()
};
export const mockPropsWithInstances = {
  ...mockProps,
  instances: [INSTANCE, ACTIVE_INSTANCE],
  instancesLoaded: true
};

export const mockPropsWithNoOperation = {
  ...mockProps,
  instances: [INSTANCE],
  instancesLoaded: true
};

export const mockPropsWithPoll = {
  ...mockPropsWithNoOperation,
  polling: {
    ids: ['1'],
    addIds: jest.fn(),
    removeIds: jest.fn()
  }
};
