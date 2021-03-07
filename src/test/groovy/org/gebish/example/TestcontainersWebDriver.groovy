package org.gebish.example

import org.openqa.selenium.remote.RemoteWebDriver
import org.testcontainers.containers.BrowserWebDriverContainer

class TestcontainersWebDriver {

    final BrowserWebDriverContainer container

    TestcontainersWebDriver(BrowserWebDriverContainer container) {
        this.container = container
        container.start()
    }

    @Delegate
    RemoteWebDriver getDriver() {
        container.webDriver
    }

    @Override
    void quit() {
        driver.quit()
        container.stop()
    }
}