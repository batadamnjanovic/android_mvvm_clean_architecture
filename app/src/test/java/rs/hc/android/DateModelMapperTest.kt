package rs.hc.android

import junit.framework.Assert.assertEquals
import org.junit.Test
import rs.hc.android.domain.model.user.UserDetails
import rs.hc.android.mapper.DataModelMapper

class DateModelMapperTest {

    companion object {
        private const val NAME = "Name"
        private const val COMPANY_NAME = "Company name"
        private const val AVATAR = "http://www.google.com"
    }

    var mapperTest: DataModelMapper = DataModelMapper()

    @Test
    fun mapToUserDetailsDataModel() {
        val userDetails = UserDetails(
            "test",
            1,
            "1",
            AVATAR,
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            false,
            NAME,
            COMPANY_NAME,
            "",
            "",
            "",
            "",
            "",
            0,
            0,
            0, 0, "", ""
        )

        val userDetailsDataModel = mapperTest.mapToUserDetailsDataModel(userDetails)

        assertEquals(userDetailsDataModel.name, NAME)
        assertEquals(userDetailsDataModel.company, COMPANY_NAME)
        assertEquals(userDetails.avatarUrl, AVATAR)
    }

}