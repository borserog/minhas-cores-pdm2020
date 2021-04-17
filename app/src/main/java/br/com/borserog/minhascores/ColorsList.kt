package br.com.borserog.minhascores

class ColorsList {
    private lateinit var colorList: ArrayList<ColorConfig>

    init {
        this.colorList = arrayListOf();
    }

    fun add(colorConfig: ColorConfig) {
        this.colorList.add(colorConfig)
    }

    fun remove(colorConfig: ColorConfig) {
        this.colorList.remove(colorConfig)
    }

    fun replace(colorConfig: ColorConfig) {
        val foundColorConfig = this.colorList.find { it.id == colorConfig.id  }
        val index = this.colorList.indexOf(foundColorConfig)

        this.colorList.set(index, colorConfig)
    }

    operator fun get(index: Int): ColorConfig {
        return this.colorList[index]
    }

    fun get(): ArrayList<ColorConfig> {
        return this.colorList
    }

    fun count(): Int {
        return this.colorList.count()
    }

}