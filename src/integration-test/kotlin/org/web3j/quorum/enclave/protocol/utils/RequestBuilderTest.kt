/*
 * Copyright 2019 Web3 Labs Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.web3j.quorum.enclave.protocol.utils

import org.junit.jupiter.api.Test
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo

class RequestBuilderTest {

    @Test
    fun testEncodeJsonRequest() {
        val path = "send"
        val json = "{\"foo\":\"bar\"}"
        val result = RequestBuilder.encodeJsonRequest(path, json)

        assertThat(result, equalTo(
                """
                POST /$path HTTP/1.1
                Host: k
                User-Agent: constellation-client
                Content-Type: application/json
                Content-Length: ${json.length}

                $json
                """.trimIndent())
        )
    }
}
