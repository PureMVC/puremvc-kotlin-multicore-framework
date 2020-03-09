//
//  ModelTestProxy.kt
//  PureMVC Kotlin Multicore
//
//  Copyright(c) 2020 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

package org.puremvc.kotlin.multicore.core

import org.puremvc.kotlin.multicore.patterns.proxy.Proxy

class ModelTestProxy : Proxy(NAME, "") {

    companion object {
        const val NAME: String = "ModelTestProxy"
        const val ON_REGISTER_CALLED: String = "OnRegister Called"
        const val ON_REMOVE_CALLED: String = "OnRemove Called"
    }

    override fun onRegister() {
        data = ON_REGISTER_CALLED
    }

    override fun onRemove() {
        data = ON_REMOVE_CALLED
    }

}