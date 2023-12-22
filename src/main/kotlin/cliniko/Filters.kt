package cliniko

import io.ktor.http.*
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun equals(field : String, value : String) : Parameters {
    return parametersOf("q[]", "$field:=$value")
}

fun inRange(field : String, minVal : String?, maxVal : String?) : Parameters {

    var result = parametersOf()

    if (minVal != null) result += parametersOf("q[]", "$field>=$minVal")
    if (maxVal != null) result += parametersOf("q[]", "$field<=$maxVal")

    return result
}

fun utcInstantInRange(field: String, minInstant: Instant?, maxInstant : Instant?) : Parameters {

    return inRange(field,
        minVal = minInstant?.toLocalDateTime(TimeZone.UTC)?.toString(),
        maxVal = maxInstant?.toLocalDateTime(TimeZone.UTC)?.toString()
    )
}