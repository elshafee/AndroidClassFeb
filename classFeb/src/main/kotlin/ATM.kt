import kotlin.system.exitProcess

class ATM {
    var name: String = ""
    private var pinCode: Int = 0
    var balance: Float = 0f
    var selectoperations: Int = 0
    var money: Int = 0

    fun enterYourCard() {
        print("Welcome to our Bank Atm\n")
        print("Please enter your name:  ")
        name = readln().toString()
        println("Welcome $name")
        print("Please enter your pinCode:  ")
        pinCode = Integer.valueOf(readln())
        print("Please enter your balance: ")
        balance = readln()!!.toFloat()
        selectOperation()

    }


    fun selectOperation() {
        print("Please Select Your Operation to proceed\n")
        print("1 for Deposit \n 2 For Withdraw \n 3 for check the Balance\n 4 for Quit\n")
        selectoperations = Integer.valueOf(readln())
        when (selectoperations) {
            1 -> dposit()
            2 -> wihDraw()
            3 -> checkBalance()
            4 -> quit()
            else -> print("invalid input")
        }
    }

    fun dposit() {
        print("Please enter the amount of money you need to deposit: ")
        money = Integer.valueOf(readln())
        balance += money
        println("Deposit done Successfully")
        checkBalance()
        doyouneedanything()

    }

    fun checkBalance() {
        println("Your Balance is: $balance")
        doyouneedanything()
    }

    fun wihDraw() {
        println("Please enter amount of money you need to withdraw: ")
        money = Integer.valueOf(readln())
        if (money <= balance) {
            balance -= money
            println("Withdraw done successfully")
            checkBalance()
        } else {
            println("Sorry, you don't have enough money")
        }
        doyouneedanything()

    }
    fun doyouneedanything(){
        println("Do you need any thing?")
        print(" 1 for yes\n 2 for no\n")
        var selected:Int= Integer.valueOf(readln())

        when(selected){
            1-> selectOperation()
            2 -> quit()
            else -> println("Out of range")
        }
    }

    fun quit() {
        println("Thanks for using our ATM, see you again")
        exitProcess(0)
    }


}

//    enter your card name, balance,pin
//seselct options
//withdraw
//deposit
//check balance