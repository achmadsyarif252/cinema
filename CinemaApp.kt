var rows =0
var columns = 0
var listCinema:MutableList<MutableList<String>> = mutableListOf()
var currentIncome = 0

fun main(){
stage3()
}
fun stage3() {
    println("Enter the number of rows:")
    rows = readln().toInt()
    println("Enter the number of seats in each row:")
    columns = readln().toInt()
    listCinema = mutableListOf()

    println()
    for (i in 1..rows) {
        val a = mutableListOf<String>()
        for (j in 1..columns) {
            a.add("S ")
        }
        listCinema.add(a)
    }

    var isExit = false
    while(!isExit){
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")
        print("> ")
        val input = readln().toInt()
        when(input){
            0 -> isExit = true
            1 -> showTheSeats()
            2 -> buyTicket()
            3 -> statistics()
        }

    }
}

fun statistics(){
    println("\nNumber of purchased tickets: ${purchasedTicket()}")
    println("Percentage: ${percentage()}%")
    println("Current income: \$$currentIncome")
    println("Total income: \$${getTotalIncom()}\n")

}

fun purchasedTicket():Int{
    var purchased = 0
    for(index in listCinema.indices){
       for (i in listCinema[index].indices){
           if(listCinema[index][i].trim()=="B") purchased++
       }
    }

    return purchased
}

fun percentage():String{
    val percentage:Float = purchasedTicket()/(rows* columns).toFloat()
    val formatPercentage = "%.2f".format(percentage*100)
    return  formatPercentage
}

fun updateCurrentIncome(income:Int){
    currentIncome+=income
}

fun getTotalIncom():Int{
    val totalSeats = rows* columns

    var frontTicketPrice = 10
    var backTicketPrice = 8

    var backAmount = (rows/2+rows%2)* columns*backTicketPrice
    var frontAmount = (rows/2)* columns*frontTicketPrice

    return if(totalSeats<=60){
        totalSeats*frontTicketPrice
    }else{
        backAmount+frontAmount
    }
}

fun showTheSeats(){
    println("Cinema:")
    for (i in 1..columns) {
        if (i == 1) print("  $i ") else print("$i ")
    }
    println()
    for(i in 0..listCinema.size-1){
        print("${i+1} ")
        for(j in 0..listCinema[i].size-1){
            print(listCinema[i][j])
        }
        println()
    }
    println()
}

fun buyTicket(){
    var rowChoosen = 0
    var selectedSeat = 0
    var isExist = false

    while(!isExist){
        print("\nEnter a row number:")
        rowChoosen = readln().toInt()

        print("Enter a seat number in that row:")
        selectedSeat = readln().toInt()
        if(rowChoosen> rows ||selectedSeat> columns){
            println("Wrong Input")
        }else if(listCinema[rowChoosen-1][selectedSeat-1].trim()=="B"){
            println("That ticket has already been purchased!")
        }else{
            isExist = true
            listCinema[rowChoosen-1][selectedSeat-1] ="B "

        }
    }

    val totalSeats = rows * columns
    val frontTicketPrice = 10
    val backTicketPrice = 8
    var ticketPrice = 0

    if(totalSeats<=60){
        ticketPrice = 10
    }else{
        ticketPrice = if(rowChoosen<= rows /2) frontTicketPrice else backTicketPrice
    }
    updateCurrentIncome(ticketPrice)

    println("\nTicket price :\$$ticketPrice\n")
}

