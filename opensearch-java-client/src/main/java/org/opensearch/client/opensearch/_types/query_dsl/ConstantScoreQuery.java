/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. Licensed under a proprietary license.
 * See the License.txt file for more information. You may not use this file
 * except in compliance with the proprietary license.
 */
/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

/*
 * Licensed to Elasticsearch B.V. under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch B.V. licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

/*
 * Modifications Copyright OpenSearch Contributors. See
 * GitHub history for details.
 */

//----------------------------------------------------
// THIS CODE IS GENERATED. MANUAL EDITS WILL BE LOST.
//----------------------------------------------------

package org.opensearch.client.opensearch._types.query_dsl;

import org.opensearch.client.json.JsonpDeserializable;
import org.opensearch.client.json.JsonpDeserializer;
import org.opensearch.client.json.JsonpMapper;
import org.opensearch.client.json.ObjectBuilderDeserializer;
import org.opensearch.client.json.ObjectDeserializer;
import org.opensearch.client.util.ApiTypeHelper;
import org.opensearch.client.util.ObjectBuilder;
import jakarta.json.stream.JsonGenerator;

import java.util.function.Function;

// typedef: _types.query_dsl.ConstantScoreQuery


@JsonpDeserializable
public class ConstantScoreQuery extends QueryBase implements QueryVariant {
	private final Query filter;

	// ---------------------------------------------------------------------------------------------

	private ConstantScoreQuery(Builder builder) {
		super(builder);

		this.filter = ApiTypeHelper.requireNonNull(builder.filter, this, "filter");

	}

	public static ConstantScoreQuery of(Function<Builder, ObjectBuilder<ConstantScoreQuery>> fn) {
		return fn.apply(new Builder()).build();
	}

	/**
	 * Query variant kind.
	 */
	@Override
	public Query.Kind _queryKind() {
		return Query.Kind.ConstantScore;
	}

	/**
	 * Required - API name: {@code filter}
	 */
	public final Query filter() {
		return this.filter;
	}

	protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

		super.serializeInternal(generator, mapper);
		generator.writeKey("filter");
		this.filter.serialize(generator, mapper);

	}

	// ---------------------------------------------------------------------------------------------

	/**
	 * Builder for {@link ConstantScoreQuery}.
	 */

	public static class Builder extends QueryBase.AbstractBuilder<Builder>
			implements
				ObjectBuilder<ConstantScoreQuery> {
		private Query filter;

		/**
		 * Required - API name: {@code filter}
		 */
		public final Builder filter(Query value) {
			this.filter = value;
			return this;
		}

		/**
		 * Required - API name: {@code filter}
		 */
		public final Builder filter(Function<Query.Builder, ObjectBuilder<Query>> fn) {
			return this.filter(fn.apply(new Query.Builder()).build());
		}

		@Override
		protected Builder self() {
			return this;
		}

		/**
		 * Builds a {@link ConstantScoreQuery}.
		 *
		 * @throws NullPointerException
		 *             if some of the required fields are null.
		 */
		public ConstantScoreQuery build() {
			_checkSingleUse();

			return new ConstantScoreQuery(this);
		}
	}

	// ---------------------------------------------------------------------------------------------

	/**
	 * Json deserializer for {@link ConstantScoreQuery}
	 */
	public static final JsonpDeserializer<ConstantScoreQuery> _DESERIALIZER = ObjectBuilderDeserializer
			.lazy(Builder::new, ConstantScoreQuery::setupConstantScoreQueryDeserializer);

	protected static void setupConstantScoreQueryDeserializer(ObjectDeserializer<ConstantScoreQuery.Builder> op) {
		QueryBase.setupQueryBaseDeserializer(op);
		op.add(Builder::filter, Query._DESERIALIZER, "filter");

	}

}
