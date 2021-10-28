package uz.lars_lion.testuz.ui.main

import com.badoo.ribs.core.Rib

interface RootMain : Rib {

    interface Dependency {
        val name: String
    }
}

