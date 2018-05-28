package org.taktik.icure.be.ehealth.logic.recipe.impl

import org.hamcrest.Matchers.greaterThanOrEqualTo
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Test
import org.taktik.freehealth.middleware.domain.RegimenItem
import org.taktik.freehealth.middleware.dto.Code
import org.taktik.icure.be.ehealth.logic.recipe.impl.KmehrPrescriptionHelper.Period
import java.time.temporal.ChronoUnit.DAYS
import java.time.temporal.ChronoUnit.WEEKS

/**
 * @author Bernard Paulus on 7/04/17.
 */
class KmehrPrescriptionHelperTest {

    @Test
    fun _null() {
        assertEquals(null, KmehrPrescriptionHelper.inferPeriodFromRegimen(null))
    }

    @Test
    fun empty() {
        assertEquals(null, KmehrPrescriptionHelper.inferPeriodFromRegimen(listOf()))
    }

    @Test(expected = Exception::class)
    fun invalid() {
        KmehrPrescriptionHelper.inferPeriodFromRegimen(listOf(RegimenItem().apply { date = 20160804; dayNumber = 1 }))
    }

    @Test
    fun specialSingleNullDayCase() {
        assertEquals(Period(DAYS, 1), KmehrPrescriptionHelper.inferPeriodFromRegimen(listOf(RegimenItem())))
    }

    @Test
    fun singleDateTreatment() {
        assertEquals(null, KmehrPrescriptionHelper.inferPeriodFromRegimen(listOf(RegimenItem().apply { date = 20160804 })))
    }

    @Test
    fun singleDayTreatment() {
        assertEquals(null, KmehrPrescriptionHelper.inferPeriodFromRegimen(listOf(RegimenItem().apply { dayNumber = 1 })))
    }

    @Test
    fun singleOnceAWeek() {
        assertEquals(Period(WEEKS, 1), KmehrPrescriptionHelper.inferPeriodFromRegimen(listOf(RegimenItem().apply {
            weekday = RegimenItem.Weekday().apply { weekDay = Code("CD-WEEKDAY", "monday") }
        })))
    }

    @Test
    fun singleWeekTreatment() {
        assertEquals(null, KmehrPrescriptionHelper.inferPeriodFromRegimen(listOf(RegimenItem().apply {
            weekday = RegimenItem.Weekday().apply { weekDay = Code("CD-WEEKDAY", "monday"); weekNumber = 1 }
        })))
    }

    @Test
    fun everyThreeDays() {
        assertEquals(Period(DAYS, 3), KmehrPrescriptionHelper.inferPeriodFromRegimen(listOf(
                RegimenItem().apply { date = 20170228 },
                RegimenItem().apply { date = 20170303 },
                RegimenItem().apply { date = 20170306 },
                RegimenItem().apply { date = 20170309 },
                RegimenItem().apply { date = 20170312 }
        )))
    }

    @Test
    fun everyThreeDaysLeapYear() {
        assertEquals(Period(DAYS, 3), KmehrPrescriptionHelper.inferPeriodFromRegimen(listOf(
                RegimenItem().apply { date = 20160228 },
                RegimenItem().apply { date = 20160302 },
                RegimenItem().apply { date = 20160305 },
                RegimenItem().apply { date = 20160308 },
                RegimenItem().apply { date = 20160311 }
        )))
    }

    @Test
    fun everyThreeDaysBroken() {
        assertEquals(null, KmehrPrescriptionHelper.inferPeriodFromRegimen(listOf(
                RegimenItem().apply { date = 20170228 },
                RegimenItem().apply { date = 20170303 },
                RegimenItem().apply { date = 20170306 },
                RegimenItem().apply { date = 20170310 }, // 4 days here
                RegimenItem().apply { date = 20170313 }
        )))
    }

    @Test
    fun mixDateNull1() {
        assertEquals(null, KmehrPrescriptionHelper.inferPeriodFromRegimen(listOf(
                RegimenItem().apply { date = 20170228 },
                RegimenItem().apply {
                    weekday = RegimenItem.Weekday().apply { weekDay = Code("CD-WEEKDAY", "monday") }
                }
        )))
    }

    @Test
    fun mixDateNull2() {
        assertEquals(null, KmehrPrescriptionHelper.inferPeriodFromRegimen(listOf(
                RegimenItem().apply { date = 20170228; },
                RegimenItem().apply { dayNumber = 1 }
        )))
    }

    @Test
    fun mixDateNull3() {
        assertEquals(null, KmehrPrescriptionHelper.inferPeriodFromRegimen(listOf(
                RegimenItem().apply {
                    weekday = RegimenItem.Weekday().apply { weekDay = Code("CD-WEEKDAY", "monday") }
                },
                RegimenItem().apply { dayNumber = 1 }
        )))
    }

    @Test
    fun administrationQuantity() {
        assertEquals(Period(DAYS, 3), KmehrPrescriptionHelper.inferPeriodFromRegimen(listOf(
                RegimenItem().apply {
                    date = 20170228
                    administratedQuantity = RegimenItem.AdministrationQuantity().apply {
                        quantity = 1.0
                        administrationUnit = Code("CD-UNIT", "bag")
                    }
                },
                RegimenItem().apply {
                    date = 20170303
                    administratedQuantity = RegimenItem.AdministrationQuantity().apply {
                        quantity = 1.0
                        administrationUnit = Code("CD-UNIT", "bag")
                    }
                }
        )))
    }

    @Test
    fun mixAdministrationQuantity1Null() {
        assertEquals(Period(DAYS, 3), KmehrPrescriptionHelper.inferPeriodFromRegimen(listOf(
                RegimenItem().apply {
                    date = 20170228
                    administratedQuantity = RegimenItem.AdministrationQuantity().apply {
                        quantity = 1.0
                        administrationUnit = Code("CD-UNIT", "bag")
                    }
                },
                RegimenItem().apply {
                    date = 20170303
                    administratedQuantity = RegimenItem.AdministrationQuantity().apply {
                        quantity = 1.0
                        administrationUnit = Code("CD-UNIT", "cap")
                    }
                }
        )))
    }

    @Test
    fun mixAdministrationQuantity2Null() {
        assertEquals(Period(DAYS, 3), KmehrPrescriptionHelper.inferPeriodFromRegimen(listOf(
                RegimenItem().apply {
                    date = 20170228
                    administratedQuantity = RegimenItem.AdministrationQuantity().apply {
                        quantity = 1.0
                        administrationUnit = Code("CD-UNIT", "bag")
                    }
                },
                RegimenItem().apply {
                    date = 20170303
                    administratedQuantity = RegimenItem.AdministrationQuantity().apply {
                        quantity = 2.0
                        administrationUnit = Code("CD-UNIT", "bag")
                    }
                }
        )))
    }

    @Test
    fun precisionBelowDaysNotSupportedDate() {
        assertEquals(null, KmehrPrescriptionHelper.inferPeriodFromRegimen(listOf(
                RegimenItem().apply { date = 20170228 ; timeOfDay = 60000},
                RegimenItem().apply { date = 20170228 ; timeOfDay = 180000}
        )))
    }

    @Test
    fun precisionBelowDaysNotSupportedDaily() {
        assertThat(KmehrPrescriptionHelper.inferPeriodFromRegimen(listOf(
                RegimenItem().apply { timeOfDay = 60000},
                RegimenItem().apply { timeOfDay = 180000}
        ))?.unit, greaterThanOrEqualTo(DAYS))
    }

    @Test(expected = Exception::class)
    fun dayIntakeMomentInvalid() {
        assertEquals(null, KmehrPrescriptionHelper.inferPeriodFromRegimen(listOf(
                RegimenItem().apply { date = 20170228; timeOfDay = 105959; dayPeriod = Code("CD-DAYPERIOD", "beforelunch")},
                RegimenItem().apply { date = 20170303; timeOfDay = 105959; dayPeriod = Code("CD-DAYPERIOD", "beforelunch")}
        )))
    }

    @Test
    fun everyThreeDaySameTimeOfDay() {
        assertEquals(Period(DAYS, 3), KmehrPrescriptionHelper.inferPeriodFromRegimen(listOf(
                RegimenItem().apply { date = 20170228; timeOfDay = 105959},
                RegimenItem().apply { date = 20170303; timeOfDay = 105959}
        )))
    }

    @Test
    fun everyThreeDayUTCNoDaylightSavingsTime() {
        assertEquals(Period(DAYS, 3), KmehrPrescriptionHelper.inferPeriodFromRegimen(listOf(
                RegimenItem().apply { date = 20170324; timeOfDay = 105959},
                RegimenItem().apply { date = 20170327; timeOfDay = 105959}
        )))
    }

    @Test
    fun everyThreeDayDifferentSameTimeOfDayNull() {
        assertEquals(null, KmehrPrescriptionHelper.inferPeriodFromRegimen(listOf(
                RegimenItem().apply { date = 20170225; timeOfDay = 105959},
                RegimenItem().apply { date = 20170228; timeOfDay = 105959},
                RegimenItem().apply { date = 20170303; timeOfDay = 110000}
        )))
    }

    @Test
    fun everyThreeDaySameDayPeriod() {
        assertEquals(Period(DAYS, 3), KmehrPrescriptionHelper.inferPeriodFromRegimen(listOf(
                RegimenItem().apply { date = 20170228; dayPeriod = Code("CD-DAYPERIOD", "beforelunch")},
                RegimenItem().apply { date = 20170303; dayPeriod = Code("CD-DAYPERIOD", "beforelunch")}
        )))
    }

    @Test
    fun everyThreeDayDifferentDayPeriodNull() {
        assertEquals(null, KmehrPrescriptionHelper.inferPeriodFromRegimen(listOf(
                RegimenItem().apply { date = 20170228; dayPeriod = Code("CD-DAYPERIOD", "morning")},
                RegimenItem().apply { date = 20170303; dayPeriod = Code("CD-DAYPERIOD", "beforelunch")}
        )))
    }

    @Test
    fun every7DaysIsEveryWeek() {
        assertEquals(Period(WEEKS, 1), KmehrPrescriptionHelper.inferPeriodFromRegimen(listOf(
                RegimenItem().apply { date = 20170301 },
                RegimenItem().apply { date = 20170308 }
        )))
    }

    @Test
    fun dayNumber() {
        assertEquals(Period(DAYS, 2), KmehrPrescriptionHelper.inferPeriodFromRegimen(listOf(
                RegimenItem().apply { dayNumber = 1 },
                RegimenItem().apply { dayNumber = 3 }
        )))
    }

    @Test
    fun dayNumberIrregular() {
        assertEquals(null, KmehrPrescriptionHelper.inferPeriodFromRegimen(listOf(
                RegimenItem().apply { dayNumber = 1 },
                RegimenItem().apply { dayNumber = 3 },
                RegimenItem().apply { dayNumber = 6 }
        )))
    }

    @Test
    fun byWeekDay() {
        assertEquals(Period(WEEKS, 2), KmehrPrescriptionHelper.inferPeriodFromRegimen(listOf(
                RegimenItem().apply { weekday = RegimenItem.Weekday().apply { weekDay = Code("CD-WEEKDAY", "monday"); weekNumber = 1 } },
                RegimenItem().apply { weekday = RegimenItem.Weekday().apply { weekDay = Code("CD-WEEKDAY", "monday"); weekNumber = 3 } }
        )))
    }

    @Test
    fun byWeekDayNoWeekNumberNull() {
        assertEquals(null, KmehrPrescriptionHelper.inferPeriodFromRegimen(listOf(
                RegimenItem().apply { weekday = RegimenItem.Weekday().apply { weekDay = Code("CD-WEEKDAY", "monday"); weekNumber = 1 } },
                RegimenItem().apply { weekday = RegimenItem.Weekday().apply { weekDay = Code("CD-WEEKDAY", "monday") } }
        )))
    }

    @Test
    fun byWeekDayDifferentDayNull() {
        assertEquals(null, KmehrPrescriptionHelper.inferPeriodFromRegimen(listOf(
                RegimenItem().apply { weekday = RegimenItem.Weekday().apply { weekDay = Code("CD-WEEKDAY", "monday"); weekNumber = 1 } },
                RegimenItem().apply { weekday = RegimenItem.Weekday().apply { weekDay = Code("CD-WEEKDAY", "tuesday"); weekNumber = 3 } }
        )))
    }

    @Test(expected = Exception::class)
    fun byWeekDayInvalid() {
        assertEquals(null, KmehrPrescriptionHelper.inferPeriodFromRegimen(listOf(
                RegimenItem().apply { weekday = RegimenItem.Weekday() }
        )))
    }

    @Test
    fun multipleDailyNotSuportedNull() {
        assertEquals(null, KmehrPrescriptionHelper.inferPeriodFromRegimen(listOf(
                RegimenItem().apply { weekday = RegimenItem.Weekday().apply { weekDay = Code("CD-WEEKDAY", "monday"); weekNumber = 1 } },
                RegimenItem().apply { weekday = RegimenItem.Weekday().apply { weekDay = Code("CD-WEEKDAY", "tuesday"); weekNumber = 3 } }
        )))
    }

    @Test
    fun dailyMultipleDayIntakeMoment() {
        assertEquals(Period(DAYS, 1), KmehrPrescriptionHelper.inferPeriodFromRegimen(listOf(
                RegimenItem().apply { timeOfDay = 105959 },
                RegimenItem().apply { dayPeriod = Code("CD-DAYPERIOD", "beforelunch")},
                RegimenItem().apply { timeOfDay = 165959 },
                RegimenItem().apply { dayPeriod = Code("CD-DAYPERIOD", "beforeldinner")}
        )))
    }
}