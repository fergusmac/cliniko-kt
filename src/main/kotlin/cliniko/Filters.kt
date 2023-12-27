package cliniko

import io.ktor.http.*
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter

fun equals(field : String, value : String) : Parameters {
    return parametersOf("q[]", "$field:=$value")
}

fun inRange(field : String, minVal : String?, maxVal : String?) : Parameters {

    var result = parametersOf()

    if (minVal != null) result += parametersOf("q[]", "$field>=$minVal")
    if (maxVal != null) result += parametersOf("q[]", "$field<=$maxVal")

    return result
}

fun instantInRange(field: String, minInstant: Instant?, maxInstant : Instant?) : Parameters {

    return inRange(field,
        minVal = minInstant?.toStringNoMillis(),
        maxVal = maxInstant?.toStringNoMillis(),
    )
}

fun Instant.toStringNoMillis() : String {
    // we have to convert to a datetime, then a java datetime, in order to be able to use the java dateformatter class
    val dt = this.toLocalDateTime(TimeZone.UTC)
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return formatter.format(dt.toJavaLocalDateTime())
}