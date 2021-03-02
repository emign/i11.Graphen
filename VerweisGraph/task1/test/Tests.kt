import org.junit.Assert
import org.junit.Test

class Test {
    val greifenberg = Knoten ("Greifenberg")
    val stegen = Knoten ("Stegen")
    val schondorf = Knoten ("Schondorf")
    val herrsching = Knoten ("Herrsching")
    val utting = Knoten ("Utting")
    val andechs = Knoten ("Andechs")
    val diessen = Knoten ("Diessen")
    val fischen = Knoten ("Fischen")

    val knoten = listOf(greifenberg, stegen, schondorf, herrsching, utting, andechs, diessen, fischen)

    val greifenBergStegen = Kante(greifenberg, stegen, 7)
    val schondorfGreifenberg = Kante(schondorf, greifenberg, 5)
    val uttingSchondorf = Kante(utting, schondorf, 5)
    val uttingHerrsching = Kante(utting, herrsching, 31)
    val diessenUtting = Kante(diessen, utting, 11)
    val diessenFischen = Kante(diessen, fischen, 7)
    val fischenAndechs = Kante(fischen, andechs, 10)
    val andechsHerrsching = Kante(andechs, herrsching, 9)
    val herrschingStegen = Kante(herrsching, stegen, 15)
    val diessenStegen = Kante(diessen, stegen, 107)
    val diessenHerrsching = Kante(diessen, herrsching, 39)

    val kanten = listOf(greifenBergStegen, schondorfGreifenberg, uttingHerrsching, uttingSchondorf, diessenFischen, diessenUtting, fischenAndechs, andechsHerrsching, herrschingStegen, diessenStegen, diessenHerrsching)

    val ammerseeRundfahrt = Graph(knoten, kanten)

    @Test
    fun testSucheKnotenByName() {
        Assert.assertTrue(ammerseeRundfahrt.sucheKnoten(knoten.random().inhalt.ortsname))

    }

    @Test
    fun testSucheKnotenByKnoten() {
        Assert.assertTrue( ammerseeRundfahrt.sucheKnoten(knoten.random()))
    }

    @Test
    fun testSucheKnotenByKnotenFail() {
        Assert.assertFalse( ammerseeRundfahrt.sucheKnoten(Knoten("Dummyknoten")))
    }

    @Test
    fun testSucheKnotenByNameFail() {
        Assert.assertFalse( ammerseeRundfahrt.sucheKnoten("München"))
    }

    @Test
    fun testNachbarFuerKnoten() {
        val liste = listOf(stegen, herrsching, fischen, utting)
        Assert.assertTrue( ammerseeRundfahrt.gibNachbarnFuer(diessen).all { it in liste })
    }

    @Test
    fun testNachbarFuerKnotenString() {
        val liste = listOf(stegen, utting, diessen, andechs)
        Assert.assertTrue( ammerseeRundfahrt.gibNachbarnFuer("Herrsching").all { it in liste })
    }

    @Test
    fun testEntfernungZwischenKnoten() {
        Assert.assertEquals(31, ammerseeRundfahrt.gibEntfernungZwischen(utting, herrsching))
    }

    @Test
    fun testEntfernungZwischenKnotenString() {
        Assert.assertEquals(107, ammerseeRundfahrt.gibEntfernungZwischen("Diessen", "Stegen"))
    }

    @Test
    fun testEnfernungRandomKante() {
        val randomKante = ammerseeRundfahrt.kanten.random()
        Assert.assertEquals(randomKante.gewicht, ammerseeRundfahrt.gibEntfernungZwischen(randomKante.start, randomKante.end))
    }
}