import org.gebish.example.TestcontainersWebDriver
import org.testcontainers.containers.BrowserWebDriverContainer

import static org.openqa.selenium.remote.DesiredCapabilities.chrome
import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL
import static org.testcontainers.shaded.org.apache.commons.io.FileUtils.ONE_GB

reportsDir = "build/reports/geb"

driver = {
    def videoDir = new File(reportsDir, "video")
    videoDir.mkdirs()

    def container = new BrowserWebDriverContainer()
            .withCapabilities(chrome())
            .withRecordingMode(RECORD_ALL, videoDir)
            .withSharedMemorySize(2 * ONE_GB) as BrowserWebDriverContainer

    container.start()

    new TestcontainersWebDriver(container)
}

cacheDriver = false
quitDriverOnBrowserReset = true