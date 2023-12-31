/*
 * Copyright 2018-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.kafka.retrytopic;

/**
 *
 * Defines the topic strategy to handle fixed delays.
 *
 * @author Tomaz Fernandes
 * @since 2.7
 * @deprecated in favor of {@link SameIntervalTopicReuseStrategy}.
 *
 */
@Deprecated
public enum FixedDelayStrategy {

	/**
	 * Uses a single topic to achieve non-blocking retry.
	 */
	SINGLE_TOPIC,

	/**
	 * Uses one separate topic per retry attempt.
	 */
	MULTIPLE_TOPICS

}
