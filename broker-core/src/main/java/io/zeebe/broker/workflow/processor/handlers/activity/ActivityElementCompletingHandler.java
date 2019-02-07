/*
 * Zeebe Broker Core
 * Copyright © 2017 camunda services GmbH (info@camunda.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.zeebe.broker.workflow.processor.handlers.activity;

import io.zeebe.broker.workflow.model.element.ExecutableActivity;
import io.zeebe.broker.workflow.processor.BpmnStepContext;
import io.zeebe.broker.workflow.processor.handlers.IOMappingHelper;
import io.zeebe.broker.workflow.processor.handlers.element.ElementCompletingHandler;
import io.zeebe.protocol.intent.WorkflowInstanceIntent;

public class ActivityElementCompletingHandler<T extends ExecutableActivity>
    extends ElementCompletingHandler<T> {

  public ActivityElementCompletingHandler() {
    super();
  }

  public ActivityElementCompletingHandler(IOMappingHelper ioMappingHelper) {
    super(ioMappingHelper);
  }

  public ActivityElementCompletingHandler(
      WorkflowInstanceIntent nextState, IOMappingHelper ioMappingHelper) {
    super(nextState, ioMappingHelper);
  }

  @Override
  protected boolean handleState(BpmnStepContext<T> context) {
    if (super.handleState(context)) {
      context.getCatchEventBehavior().unsubscribeFromEvents(context.getRecord().getKey(), context);
      return true;
    }

    return false;
  }
}
