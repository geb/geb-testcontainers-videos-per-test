package org.gebish.example

import geb.spock.GebSpec

class VideoRecordingSpec extends GebSpec {

    def cleanup() {
        def testcontainersWebDriver = driver as TestcontainersWebDriver
        testcontainersWebDriver.afterTest(
                specificationContext.currentIteration.name, specificationContext.currentSpec.name
        )
    }

    def 'go to gebish.org'() {
        expect:
        browser.go('https://gebish.org')
    }

    def 'go to spockframework.org'() {
        expect:
        browser.go('https://spockframework.org/')
    }
}
