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
package org.web3j.quorum

import java.io.File
import java.util.Base64
import okhttp3.OkHttpClient
import org.web3j.protocol.http.HttpService
import org.web3j.quorum.enclave.Constellation
import org.web3j.quorum.enclave.Tessera
import org.web3j.quorum.enclave.protocol.EnclaveService
import java.util.Arrays

/**
 * Common parameters for unit tests.
 */

// ASCII base 64 encoded payload
val PAYLOAD: String = Base64.getEncoder().encodeToString("message payload1".toByteArray())
val localhost = "http://localhost"

// Tessera Node configuration
val quorum1T = Node(
        "0xed9d02e382b34818e88b88a309c7fe71e65f419d",
        Arrays.asList(
                "BULeR8JyUWhiuuCMU/HLA0Q5pzkYT+cHII3ZKBey3Bo="),
        "http://localhost:22000")

val quorum2T = Node(
        "0xca843569e3427144cead5e4d5999a3d0ccf92b8e",
        Arrays.asList(
                "QfeDAys9MPDs2XHExtc84jKGHxZg/aj52DTh0vtA3Xc="),
        "http://localhost:22001")
val quorum3T = Node(
        "0x0fbdc686b912d7722dc86510934589e0aaf3b55a",
        Arrays.asList(
                "1iTZde/ndBHvzhcl7V68x44Vx7pl8nwx9LqnM/AfJUg="),
        "http://localhost:22002")

val quorum4T = Node(
        "0x9186eb3d20cbd1f5f992a950d808c4495153abd5",
        Arrays.asList(
                "oNspPPgszVUFw0qmGFfWwh1uxVUXgvBxleXORHj07g8="),
        "http://localhost:22003")

val nodesT = Arrays.asList(
        quorum1T, quorum2T, quorum3T, quorum4T)
val quorumTessera = Quorum.build(HttpService(quorum1T.url))
val tessera = Arrays.asList(Tessera(EnclaveService(localhost, 9081), quorumTessera),
        Tessera(EnclaveService(localhost, 9082), Quorum.build(HttpService(quorum2T.url))),
        Tessera(EnclaveService(localhost, 9083), Quorum.build(HttpService(quorum3T.url))),
        Tessera(EnclaveService(localhost, 9084), Quorum.build(HttpService(quorum4T.url))))
val upCheckTessera = Tessera(EnclaveService(localhost, 9001), quorumTessera)

// Constellation configuration parameters
private val quorum1C = Node(
        "0xed9d02e382b34818e88b88a309c7fe71e65f419d",
        Arrays.asList(
                "BULeR8JyUWhiuuCMU/HLA0Q5pzkYT+cHII3ZKBey3Bo="),
        "http://localhost:22000")

private val quorum2C = Node(
        "0xca843569e3427144cead5e4d5999a3d0ccf92b8e",
        Arrays.asList(
                "QfeDAys9MPDs2XHExtc84jKGHxZg/aj52DTh0vtA3Xc="),
        "http://localhost:22001")

private val quorum3C = Node(
        "0x0fbdc686b912d7722dc86510934589e0aaf3b55a",
        Arrays.asList(
                "1iTZde/ndBHvzhcl7V68x44Vx7pl8nwx9LqnM/AfJUg="),
        "http://localhost:22002")

private val quorum4C = Node(
        "0x9186eb3d20cbd1f5f992a950d808c4495153abd5",
        Arrays.asList(
                "oNspPPgszVUFw0qmGFfWwh1uxVUXgvBxleXORHj07g8="),
        "http://localhost:22003")

val nodesC = Arrays.asList(
        quorum1C, quorum2C, quorum3C, quorum4C)

val constellationIpcPath1 = "/Users/sebastianraba/Desktop/work/web3js-quorum/constellation/data/constellation.ipc"
val constellationIpcPath2 = "/Users/sebastianraba/Desktop/work/web3js-quorum/constellation/data1/constellation.ipc"
val constellationIpcPath3 = "/Users/sebastianraba/Desktop/work/web3js-quorum/constellation/data2/constellation.ipc"
val constellationIpcPath4 = "/Users/sebastianraba/Desktop/work/web3js-quorum/constellation/data3/constellation.ipc"

val client = OkHttpClient.Builder()
        .socketFactory(UnixDomainSocketFactory(File(constellationIpcPath1)))
        .build()
val client1 = OkHttpClient.Builder()
        .socketFactory(UnixDomainSocketFactory(File(constellationIpcPath2)))
        .build()

val client2 = OkHttpClient.Builder()
        .socketFactory(UnixDomainSocketFactory(File(constellationIpcPath3)))
        .build()
val client3 = OkHttpClient.Builder()
        .socketFactory(UnixDomainSocketFactory(File(constellationIpcPath4)))
        .build()
val constellation = Arrays.asList(Constellation(EnclaveService("http://localhost", 9020, client), Quorum.build(HttpService(quorum1C.url))),
        Constellation(EnclaveService("http://localhost", 9020, client1), Quorum.build(HttpService(quorum2C.url))),
        Constellation(EnclaveService("http://localhost", 9020, client2), Quorum.build(HttpService(quorum3C.url))),
        Constellation(EnclaveService("http://localhost", 9020, client3), Quorum.build(HttpService(quorum4C.url))))

// ASCII base 64 encoded public keys for our transaction managers
const val TM1_PUBLIC_KEY = "BULeR8JyUWhiuuCMU/HLA0Q5pzkYT+cHII3ZKBey3Bo="
const val TM2_PUBLIC_KEY = "QfeDAys9MPDs2XHExtc84jKGHxZg/aj52DTh0vtA3Xc="
