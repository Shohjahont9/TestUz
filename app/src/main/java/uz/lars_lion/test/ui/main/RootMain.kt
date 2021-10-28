package uz.lars_lion.test.ui.main

import com.badoo.ribs.core.Rib

interface RootMain : Rib {

    interface Dependency {
        val name: String

    }
}
