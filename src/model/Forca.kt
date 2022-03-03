package model

class Forca(private var palavra: String, private var dica: String) {
    private var descobertas   = ""
    private var palavraOculta = mutableListOf<Char>()
    private var tentativa     = palavra.length
    private var acertos       = 0

    fun getPalavra(): String {
        return this.palavra
    }

    fun setPalavra(palavra: String) {
        this.palavra = palavra
    }

    fun getDica(): String {
        return this.dica
    }

    fun setDicas(dica: String) {
        this.dica = dica
    }

    fun getDescobertos(): String {
        return this.descobertas
    }

    fun setDescobertos(descobertos: String) {
        this.descobertas = descobertos
    }

    fun getPalavraOculta(): String {
        return this.palavraOculta.joinToString(" ")
    }

    fun getTentativa(): Int {
        return this.tentativa
    }

    fun getAcertos(): Int {
        return this.acertos
    }

    fun iniciarJogo() {
        this.esconderPalavra()
    }

    fun esconderPalavra() {
        for (i in 0 until this.palavra.length) {
            this.palavraOculta.add('_')
        }
    }

    fun letraDistinta(): Int {
        var letrasDistinct = ""

        for (i in 0 until this.palavra.length) {
            if (!letrasDistinct.contains(this.palavra[i])) {
                letrasDistinct += String.format(
                    "%s",
                    this.palavra[i]
                )
            }
        }

        return letrasDistinct.length
    }

    fun arriscarLetra(letra: String) {
        try {
            var existe = false

            this.testarLetra(letra)

            for (i in 0 until this.palavra.length) {
                if (letra[0].uppercaseChar() == this.palavra[i].uppercaseChar()) {
                    this.acertos++
                    this.palavraOculta[i] = letra[0]
                    existe = true
                }

            }

            if (!existe) {
                this.tentativa--
            }
        }
        catch (e: Throwable) {
            println(e.message)
        }
    }

    fun testarLetra(letra: String) {
        if (this.descobertas.contains(letra)) {
            throw Throwable(String.format(
                "Letra %s já digitada, tente outra letra",
                letra
            ))
        }
        else if (letra.length > 1) {
            throw Throwable(String.format(
                "Por favor digite apenas uma letra"
            ))
        }
        else {
            this.descobertas += String.format("%s ", letra)
        }
    }

}