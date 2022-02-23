/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. Licensed under a commercial license.
 * You may not use this file except in compliance with the commercial license.
 */

import {useLocation, useNavigate} from 'react-router-dom';

const HashRouterMigrator: React.FC = () => {
  const location = useLocation();
  const navigate = useNavigate();

  if (location.hash !== '') {
    navigate(location.hash.replace(/^#/, ''), {replace: true});
  }

  return null;
};

export {HashRouterMigrator};
