class AndroidDevelopers: Employee(), NDA, Contract {

    init {
        netSalary()
        workingHours()
        daysOff()
        benifits()
        bandNumberOne()
        bandNumberTwo()
        bandNumberThree()
        bandNumberTFour()
        doNotShareContentWithOther()

    }

    override fun netSalary() {
        println("Your net salary is 20000 EGP")
    }

    override fun benifits() {
        println("You can get 25% benifits every year on your salary")
    }

    override fun daysOff() {
        println("You have 21 days off per year")
    }

    override fun workingHours() {
        println("You have to work 30 hrs per week")
    }

    override fun bandNumberOne() {
        super<NDA>.bandNumberOne()
    }



    override fun bandNumberTwo() {
        println("Band number 2")
    }

    override fun bandNumberThree() {
        println("Band number 3")
    }

    override fun bandNumberTFour() {
        println("Band number 4")
    }

    override fun doNotShareContentWithOther() {
        println("don't share with others")
    }
}