package org.gebish.example

import geb.test.ManagedGebTest
import org.spockframework.runtime.extension.IGlobalExtension
import org.spockframework.runtime.model.SpecInfo
import org.testcontainers.containers.VncRecordingContainer

import static org.testcontainers.containers.VncRecordingContainer.VncRecordingFormat.FLV

class VncRecordingExtension implements IGlobalExtension {
    @Override
    void start() {
    }

    @Override
    void visitSpec(SpecInfo spec) {
        if (ManagedGebTest.isAssignableFrom(spec.reflection)) {
            spec.allFeatures*.addIterationInterceptor {
                def testManager = (it.instance as ManagedGebTest).testManager
                def browser = testManager.browser
                def container = (browser.driver as TestcontainersWebDriver).container
                def videoFormat = FLV
                def vncContainer = new VncRecordingContainer(container)
                        .withVncPassword(container.password)
                        .withVncPort(container.port)
                        .withVideoFormat(FLV)

                vncContainer.start()
                try {
                    it.proceed()
                } finally {
                    def filename = it.iteration.name.replaceAll("(?U)[^\\w\\s-]", "_")
                    def recordingFile = new File(browser.reportGroupDir, "${filename}.${videoFormat.filenameExtension}")
                    vncContainer.saveRecordingToFile(recordingFile)
                    vncContainer.stop()
                }
            }
        }
    }

    @Override
    void stop() {
    }
}
