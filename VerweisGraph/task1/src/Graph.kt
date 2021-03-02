class Graph(val knoten : List<Knoten>, val kanten : List<Kante>) {
    fun sucheKnoten(knoten : Knoten) : Boolean  = this.knoten.contains(knoten)

    fun sucheKnoten(knoten : String) : Boolean  = this.knoten.filter { it.inhalt.ortsname == knoten }.isNotEmpty()

    fun gibNachbarnFuer(knoten : Knoten) : List<Knoten> {
        var nachbarnBeiDenenStart = emptyList<Knoten>().toMutableList()
        var nachbarnBeiDenenEnde = emptyList<Knoten>().toMutableList()
        kanten.forEach {
            if (it.start == knoten){
                nachbarnBeiDenenStart.add(it.end)
            }
            if (it.end == knoten){
                nachbarnBeiDenenEnde.add(it.start)
            }
        }
        return nachbarnBeiDenenEnde + nachbarnBeiDenenStart
    }

    fun gibNachbarnFuer(knoten : String) : List<Knoten> {
        var nachbarnBeiDenenStart = emptyList<Knoten>().toMutableList()
        var nachbarnBeiDenenEnde = emptyList<Knoten>().toMutableList()
        kanten.forEach {
            if (it.start.inhalt.ortsname == knoten){
                nachbarnBeiDenenStart.add(it.end)
            }
            if (it.end.inhalt.ortsname == knoten){
                nachbarnBeiDenenEnde.add(it.start)
            }
        }
        return nachbarnBeiDenenEnde + nachbarnBeiDenenStart
    }

    fun gibEntfernungZwischen(start : Knoten, ziel: Knoten) : Int {
        var entfernung = Int.MAX_VALUE
        kanten.forEach {
            if (it.start == start && it.end == ziel || it.start == ziel && it.end == start) {
                entfernung = it.gewicht
            }
        }
        return entfernung
    }

    fun gibEntfernungZwischen(start : String, ziel: String) : Int {
        var entfernung = Int.MAX_VALUE
        kanten.forEach {
            if (it.start.inhalt.ortsname == start && it.end.inhalt.ortsname == ziel || it.start.inhalt.ortsname == ziel && it.end.inhalt.ortsname == start) {
                entfernung = it.gewicht
            }
        }
        return entfernung
    }
}