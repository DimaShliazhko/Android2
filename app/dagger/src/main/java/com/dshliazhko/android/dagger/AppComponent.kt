package com.dshliazhko.android.dagger

import dagger.Component
import dagger.Module
import dagger.Provides


//графф зависимостей
@Component( modules = [AppModule :: class])
interface AppComponent {

    // передается класс в который нужо доставить зависимость
    fun inject(mainActivity: MainActivity)

    val computer : Computer
}


// объявление зависимостей каким образом они подставляются
@Module
object AppModule {

    // функции для предоставления
    @Provides
    fun providerComputer(
            processor: Processor,
            ram: RAM,
            motherBoard: MotherBoard
    ): Computer {
        TODO()

        return Computer(
                processor = processor,
                motherBoard = motherBoard,
                ram = ram,
        )
    }

    @Provides
    fun providerRAM(): RAM {
        return RAM()
    }

    @Provides
    fun providerMotherBoard(): MotherBoard {
        return MotherBoard()
    }
    @Provides
    fun providerProcessor(): Processor {
        return Processor()
    }

}