
sealed class Option<out T> {
    data class Some<out T>(val value: T) : Option<T>()
    object None: Option<Nothing>()
}

class Account(val accountId: String, var balance: Double) {
    fun deposit(amount: Double): Option<Account> {
        balance += amount
        return Option.Some(this)
    }

    fun withdraw(amount: Double): Option<Account> {
        if (amount > balance) return Option.None
        balance -= amount
        return Option.Some(this)
    }

    fun transfer(amount: Double, toAccount: Account): Option<Account> {
        return withdraw(amount).flatMap {
            toAccount.deposit(amount)
        }
    }
}

fun <T, R> Option<T>.flatMap(f: (T) -> Option<R>): Option<R> {
    return when(this) {
        is Option.Some -> f(value)
        is Option.None -> Option.None
    }
}

fun main() {
    val account1 = Account("Account1", 1000.0)
    val account2 = Account("Account2", 0.0)
    val amount = 500.0

    val depositResult = account1.deposit(amount)
    val withdrawResult = account1.withdraw(amount)
    val transferResult = account1.transfer(amount, account2)

    when (depositResult) {
        is Option.Some -> println("Deposited $amount to ${depositResult.value.accountId}")
        is Option.None -> println("Deposit failed")
    }

    when (withdrawResult) {
        is Option.Some -> println("Withdrew $amount from ${withdrawResult.value.accountId}")
        is Option.None -> println("Withdrawal failed")
    }

    when (transferResult) {
        is Option.Some -> println("Transferred $amount from ${transferResult.value.accountId} to ${account2.accountId}")
        is Option.None -> println("Transfer failed")
    }
}