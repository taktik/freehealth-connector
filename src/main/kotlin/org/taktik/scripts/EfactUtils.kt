package org.taktik.scripts

import org.taktik.freehealth.middleware.format.efact.BelgianInsuranceInvoicingFormatReader
import java.io.File

fun main(args: Array<String>) {
    args.forEach { fn ->
        val f = File(fn)
        val reader = f.bufferedReader()

        File(f.parent, f.nameWithoutExtension+".yml").bufferedWriter().let { w ->
            w.write(BelgianInsuranceInvoicingFormatReader("fr").parse(reader)!!.map { it.toString() }
                .joinToString("\n"))
            w.close()
        }

        reader.close()
    }
}
