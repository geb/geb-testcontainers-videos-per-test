package org.gebish.example

import geb.spock.GebReportingSpec

class VideoRecordingSpec extends GebReportingSpec {

    def 'go to gebish.org'() {
        expect:
        browser.go('https://gebish.org')
    }

    def 'go to spockframework.org'() {
        expect:
        browser.go('https://spockframework.org/')
        sleep 1000
    }
}
