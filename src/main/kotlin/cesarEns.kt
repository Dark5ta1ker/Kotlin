import kotlin.system.exitProcess

fun main() {
    chooseAction()
}

fun textUserInput() : List<Char> {
    println("Введите текст")
    val textInput = listOf(readLine() ?: "").map { it.lowercase() }.flatMap { it.toList() }
    return checkLetters(textInput)
}

fun checkLetters(letters: List<Char>) : List<Char> {
    val specialChars = listOf('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '+', '-', '=', '[', ']', '{', '}', ';', ':',
        ',', '.', '<', '>', '/', '?', '|', '\\', '`', '~', '"', '\'')

    val approvedLetters = letters.filter { it !in specialChars }
    val filteredLetters = letters.filter { it in specialChars }

    if (approvedLetters.isEmpty()){
        println("Ошибка: нет допустимых символов")
        exitProcess(1)
    }

    if (filteredLetters.isNotEmpty()){
        println("Найдены недопустимые символы: $filteredLetters")
    }
    return approvedLetters
}

fun keyInput() : Int{
    println("Введите ключ")
    val keyInput: Int = readln().toInt()
    return keyInput
}

fun chooseLang() : List<Char>{
    val enAlphabet = listOf<Char>(
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' ')

    val ruAlphabet = listOf<Char>(
        'а', 'б', 'в','г','д','е','ж','з','и','й','к','л','м','н','о','п',
        'р','с','т','у','ф','х','ц','ч','ш','щ','ы','ь','э','ю','я',' ')

    while (true) {
        println("Выберите язык: \n 1 - русский \n 2 - английский")
        val number = readln().toIntOrNull()
        when (number) {
            1 -> { println("Выбран русский"); return ruAlphabet }
            2 -> { println("Выбран английский"); return enAlphabet }
            else -> println("Некорректный выбор")
        }
    }
}

fun chooseAction(){
    println("Выберите действие: \n 1 - Шифровка \n 2 - дешифровка")
    val num = readln().toIntOrNull()
    when (num) {
        1 -> { println("Выбрана шифровка"); println(cesarEns(chooseLang(),textUserInput(), keyInput() )) }
        2 -> { println("Выбрана дешифровка"); println(cesarDes(chooseLang(),textUserInput(), keyInput() )) }
        else -> { println("Некорректный выбор"); exitProcess(1) }
    }
}

fun cesarEns(alphabet: List<Char>, text: List<Char>, key: Int ) : String {
    val finalText = mutableListOf<Char>()
    for (char in text) {
        if (char in alphabet){
            val i = alphabet.indexOf(char)
            if (alphabet[(i + key) % alphabet.size] == ' ') {
                finalText.add('_')
            } else {
                finalText.add(alphabet[(i + key) % alphabet.size])
            }
        }
    }
    return finalText.joinToString("")
}


fun cesarDes(alphabet: List<Char>, text: List<Char>, key: Int ) : String {
    val finalText = mutableListOf<Char>()
    val key = alphabet.size - key
    for (char in text) {
        if (char in alphabet){
            val i = alphabet.indexOf(char)
            if (alphabet[(i + key) % alphabet.size] == ' ') {
                finalText.add('_')
            } else {
                finalText.add(alphabet[(i + key) % alphabet.size])
            }
        }
    }
    return finalText.joinToString("")
}