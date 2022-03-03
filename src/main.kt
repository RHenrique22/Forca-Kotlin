import model.Forca
import java.util.*

fun main() {

    val input   = Scanner(System.`in`)

    print("Digite uma palavra: ")
    var palavra = input.nextLine()

    print("Digite uma dica: ")
    var dica    = input.nextLine()

    check(palavra.isNotEmpty() && dica.isNotEmpty()) {
        "É necessária uma palavra e uma dica"
    }

    var forca   = Forca(palavra, dica)

    print("Deseja jogar (S/N): ")
    var jogar   = input.nextLine().uppercase()

    forca.iniciarJogo()

    if (jogar.contains('S')) {
        println(String.format(
            "%nDica: %s",
            forca.getDica().uppercase())
        )

        println(forca.getPalavraOculta())
        println(String.format(
            "A palavra tem %d letra(s) e %d são distintas%n",
            forca.getPalavra().length, forca.letraDistinta()
        ))

        print("Digite uma letra: ")
        var letra = input.nextLine().uppercase()
        println()

        while (true) {
            try {
                forca.arriscarLetra(letra)
                if (forca.getAcertos() < forca.getPalavra().length && forca.getTentativa() > 0) {
                    println(String.format(
                        "Dica: %s",
                        forca.getDica().uppercase())
                    )

                    println(forca.getPalavraOculta())

                    println(String.format(
                        "A palavra tem %d letra(s)  e %d são distintas",
                        forca.getPalavra().length, forca.letraDistinta()
                    ))

                    println(String.format(
                        "Letras já usadas: [%s]", forca.getDescobertos()
                    ))

                    println(String.format(
                        "Acertos: %d Tentativas: %d%n",
                        forca.getAcertos(), forca.getTentativa()
                    ))

                    print("Digite uma letra: ")
                    letra = input.nextLine().uppercase()
                    println()
                }
                else {
                    if (forca.getAcertos() == forca.getPalavra().length) {
                        println(String.format(
                            "A palavra era: %s",
                            forca.getPalavraOculta()
                        ))
                        print(String.format(
                            "%nGANHOU"
                        ))
                    }
                    else if (forca.getTentativa() == 0) {
                        println(String.format(
                            "A palavra era: %s",
                            forca.getPalavra()
                        ))
                        print(String.format(
                            "%nPERDEU"
                        ))
                    }
                    break
                }
            }
            catch (e: Throwable) {
                println(e.message)
            }
        }
    }
    else {
        println("Obrigado volte quando quiser jogar :)")
    }
}