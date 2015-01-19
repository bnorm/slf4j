/**
 * Copyright (c) 2004-2011 QOS.ch
 * All rights reserved.
 *
 * Permission is hereby granted, free  of charge, to any person obtaining
 * a  copy  of this  software  and  associated  documentation files  (the
 * "Software"), to  deal in  the Software without  restriction, including
 * without limitation  the rights to  use, copy, modify,  merge, publish,
 * distribute,  sublicense, and/or sell  copies of  the Software,  and to
 * permit persons to whom the Software  is furnished to do so, subject to
 * the following conditions:
 *
 * The  above  copyright  notice  and  this permission  notice  shall  be
 * included in all copies or substantial portions of the Software.
 *
 * THE  SOFTWARE IS  PROVIDED  "AS  IS", WITHOUT  WARRANTY  OF ANY  KIND,
 * EXPRESS OR  IMPLIED, INCLUDING  BUT NOT LIMITED  TO THE  WARRANTIES OF
 * MERCHANTABILITY,    FITNESS    FOR    A   PARTICULAR    PURPOSE    AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE,  ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.slf4j.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.slf4j.spi.LocationAwareLogger;

/**
 * Test the proper behavior of the JDK14LoggerAdapter class.
 *
 * @author Ceki Gulcu
 * @author Brian Norman
 */
public class JDK14LoggerAdapterTest {

    private ListHandler listHandler;

    @Before
    public void setUp() {
        this.listHandler = new ListHandler();

        java.util.logging.Logger testRoot = java.util.logging.Logger.getLogger("test");
        testRoot.setLevel(Level.ALL);
        testRoot.addHandler(listHandler);
        testRoot.setUseParentHandlers(false); // This keeps JUL quite

        java.util.logging.Logger.getLogger("test.level.all").setLevel(Level.ALL);
        java.util.logging.Logger.getLogger("test.level.trace").setLevel(Level.FINEST);
        java.util.logging.Logger.getLogger("test.level.debug").setLevel(Level.FINE);
        java.util.logging.Logger.getLogger("test.level.info").setLevel(Level.INFO);
        java.util.logging.Logger.getLogger("test.level.warn").setLevel(Level.WARNING);
        java.util.logging.Logger.getLogger("test.level.error").setLevel(Level.SEVERE);
        java.util.logging.Logger.getLogger("test.level.off").setLevel(Level.OFF);
    }

    @Test
    public void testRootLogger() {
        Logger loggerRootSLF4J = LoggerFactory.getLogger("root");
        Logger loggerRootJDK14 = LoggerFactory.getLogger("");

        Assert.assertEquals("", loggerRootSLF4J.getName());
        Assert.assertEquals("", loggerRootJDK14.getName());
        Assert.assertEquals(loggerRootSLF4J, loggerRootJDK14);
    }

    @Test
    public void testIsEnabled() {
        Marker marker = MarkerFactory.getMarker("marker");
        Marker markerOther = MarkerFactory.getMarker("marker.other");

        Logger loggerAll = LoggerFactory.getLogger("test.level.all");
        Assert.assertTrue(loggerAll.isTraceEnabled());
        Assert.assertTrue(loggerAll.isTraceEnabled(marker));
        Assert.assertTrue(loggerAll.isTraceEnabled(markerOther));
        Assert.assertTrue(loggerAll.isDebugEnabled());
        Assert.assertTrue(loggerAll.isDebugEnabled(marker));
        Assert.assertTrue(loggerAll.isDebugEnabled(markerOther));
        Assert.assertTrue(loggerAll.isInfoEnabled());
        Assert.assertTrue(loggerAll.isInfoEnabled(marker));
        Assert.assertTrue(loggerAll.isInfoEnabled(markerOther));
        Assert.assertTrue(loggerAll.isWarnEnabled());
        Assert.assertTrue(loggerAll.isWarnEnabled(marker));
        Assert.assertTrue(loggerAll.isWarnEnabled(markerOther));
        Assert.assertTrue(loggerAll.isErrorEnabled());
        Assert.assertTrue(loggerAll.isErrorEnabled(marker));
        Assert.assertTrue(loggerAll.isErrorEnabled(markerOther));

        Logger loggerTrace = LoggerFactory.getLogger("test.level.trace");
        Assert.assertTrue(loggerTrace.isTraceEnabled());
        Assert.assertTrue(loggerTrace.isTraceEnabled(marker));
        Assert.assertTrue(loggerTrace.isTraceEnabled(markerOther));
        Assert.assertTrue(loggerTrace.isDebugEnabled());
        Assert.assertTrue(loggerTrace.isDebugEnabled(marker));
        Assert.assertTrue(loggerTrace.isDebugEnabled(markerOther));
        Assert.assertTrue(loggerTrace.isInfoEnabled());
        Assert.assertTrue(loggerTrace.isInfoEnabled(marker));
        Assert.assertTrue(loggerTrace.isInfoEnabled(markerOther));
        Assert.assertTrue(loggerTrace.isWarnEnabled());
        Assert.assertTrue(loggerTrace.isWarnEnabled(marker));
        Assert.assertTrue(loggerTrace.isWarnEnabled(markerOther));
        Assert.assertTrue(loggerTrace.isErrorEnabled());
        Assert.assertTrue(loggerTrace.isErrorEnabled(marker));
        Assert.assertTrue(loggerTrace.isErrorEnabled(markerOther));

        Logger loggerDebug = LoggerFactory.getLogger("test.level.debug");
        Assert.assertFalse(loggerDebug.isTraceEnabled());
        Assert.assertFalse(loggerDebug.isTraceEnabled(marker));
        Assert.assertFalse(loggerDebug.isTraceEnabled(markerOther));
        Assert.assertTrue(loggerDebug.isDebugEnabled());
        Assert.assertTrue(loggerDebug.isDebugEnabled(marker));
        Assert.assertTrue(loggerDebug.isDebugEnabled(markerOther));
        Assert.assertTrue(loggerDebug.isInfoEnabled());
        Assert.assertTrue(loggerDebug.isInfoEnabled(marker));
        Assert.assertTrue(loggerDebug.isInfoEnabled(markerOther));
        Assert.assertTrue(loggerDebug.isWarnEnabled());
        Assert.assertTrue(loggerDebug.isWarnEnabled(marker));
        Assert.assertTrue(loggerDebug.isWarnEnabled(markerOther));
        Assert.assertTrue(loggerDebug.isErrorEnabled());
        Assert.assertTrue(loggerDebug.isErrorEnabled(marker));
        Assert.assertTrue(loggerDebug.isErrorEnabled(markerOther));

        Logger loggerInfo = LoggerFactory.getLogger("test.level.info");
        Assert.assertFalse(loggerInfo.isTraceEnabled());
        Assert.assertFalse(loggerInfo.isTraceEnabled(marker));
        Assert.assertFalse(loggerInfo.isTraceEnabled(markerOther));
        Assert.assertFalse(loggerInfo.isDebugEnabled());
        Assert.assertFalse(loggerInfo.isDebugEnabled(marker));
        Assert.assertFalse(loggerInfo.isDebugEnabled(markerOther));
        Assert.assertTrue(loggerInfo.isInfoEnabled());
        Assert.assertTrue(loggerInfo.isInfoEnabled(marker));
        Assert.assertTrue(loggerInfo.isInfoEnabled(markerOther));
        Assert.assertTrue(loggerInfo.isWarnEnabled());
        Assert.assertTrue(loggerInfo.isWarnEnabled(marker));
        Assert.assertTrue(loggerInfo.isWarnEnabled(markerOther));
        Assert.assertTrue(loggerInfo.isErrorEnabled());
        Assert.assertTrue(loggerInfo.isErrorEnabled(marker));
        Assert.assertTrue(loggerInfo.isErrorEnabled(markerOther));

        Logger loggerWarn = LoggerFactory.getLogger("test.level.warn");
        Assert.assertFalse(loggerWarn.isTraceEnabled());
        Assert.assertFalse(loggerWarn.isTraceEnabled(marker));
        Assert.assertFalse(loggerWarn.isTraceEnabled(markerOther));
        Assert.assertFalse(loggerWarn.isDebugEnabled());
        Assert.assertFalse(loggerWarn.isDebugEnabled(marker));
        Assert.assertFalse(loggerWarn.isDebugEnabled(markerOther));
        Assert.assertFalse(loggerWarn.isInfoEnabled());
        Assert.assertFalse(loggerWarn.isInfoEnabled(marker));
        Assert.assertFalse(loggerWarn.isInfoEnabled(markerOther));
        Assert.assertTrue(loggerWarn.isWarnEnabled());
        Assert.assertTrue(loggerWarn.isWarnEnabled(marker));
        Assert.assertTrue(loggerWarn.isWarnEnabled(markerOther));
        Assert.assertTrue(loggerWarn.isErrorEnabled());
        Assert.assertTrue(loggerWarn.isErrorEnabled(marker));
        Assert.assertTrue(loggerWarn.isErrorEnabled(markerOther));

        Logger loggerError = LoggerFactory.getLogger("test.level.error");
        Assert.assertFalse(loggerError.isTraceEnabled());
        Assert.assertFalse(loggerError.isTraceEnabled(marker));
        Assert.assertFalse(loggerError.isTraceEnabled(markerOther));
        Assert.assertFalse(loggerError.isDebugEnabled());
        Assert.assertFalse(loggerError.isDebugEnabled(marker));
        Assert.assertFalse(loggerError.isDebugEnabled(markerOther));
        Assert.assertFalse(loggerError.isInfoEnabled());
        Assert.assertFalse(loggerError.isInfoEnabled(marker));
        Assert.assertFalse(loggerError.isInfoEnabled(markerOther));
        Assert.assertFalse(loggerError.isWarnEnabled());
        Assert.assertFalse(loggerError.isWarnEnabled(marker));
        Assert.assertFalse(loggerError.isWarnEnabled(markerOther));
        Assert.assertTrue(loggerError.isErrorEnabled());
        Assert.assertTrue(loggerError.isErrorEnabled(marker));
        Assert.assertTrue(loggerError.isErrorEnabled(markerOther));

        Logger loggerOff = LoggerFactory.getLogger("test.level.off");
        Assert.assertFalse(loggerOff.isTraceEnabled());
        Assert.assertFalse(loggerOff.isTraceEnabled(marker));
        Assert.assertFalse(loggerOff.isTraceEnabled(markerOther));
        Assert.assertFalse(loggerOff.isDebugEnabled());
        Assert.assertFalse(loggerOff.isDebugEnabled(marker));
        Assert.assertFalse(loggerOff.isDebugEnabled(markerOther));
        Assert.assertFalse(loggerOff.isInfoEnabled());
        Assert.assertFalse(loggerOff.isInfoEnabled(marker));
        Assert.assertFalse(loggerOff.isInfoEnabled(markerOther));
        Assert.assertFalse(loggerOff.isWarnEnabled());
        Assert.assertFalse(loggerOff.isWarnEnabled(marker));
        Assert.assertFalse(loggerOff.isWarnEnabled(markerOther));
        Assert.assertFalse(loggerOff.isErrorEnabled());
        Assert.assertFalse(loggerOff.isErrorEnabled(marker));
        Assert.assertFalse(loggerOff.isErrorEnabled(markerOther));

        // Logger 'test' is set to Level ALL so this should also be ALL.
        Logger loggerRandom = LoggerFactory.getLogger("test.random");
        Assert.assertTrue(loggerRandom.isTraceEnabled());
        Assert.assertTrue(loggerRandom.isTraceEnabled(marker));
        Assert.assertTrue(loggerRandom.isTraceEnabled(markerOther));
        Assert.assertTrue(loggerRandom.isDebugEnabled());
        Assert.assertTrue(loggerRandom.isDebugEnabled(marker));
        Assert.assertTrue(loggerRandom.isDebugEnabled(markerOther));
        Assert.assertTrue(loggerRandom.isInfoEnabled());
        Assert.assertTrue(loggerRandom.isInfoEnabled(marker));
        Assert.assertTrue(loggerRandom.isInfoEnabled(markerOther));
        Assert.assertTrue(loggerRandom.isWarnEnabled());
        Assert.assertTrue(loggerRandom.isWarnEnabled(marker));
        Assert.assertTrue(loggerRandom.isWarnEnabled(markerOther));
        Assert.assertTrue(loggerRandom.isErrorEnabled());
        Assert.assertTrue(loggerRandom.isErrorEnabled(marker));
        Assert.assertTrue(loggerRandom.isErrorEnabled(markerOther));

        // Logger 'test' is set to Level ALL so this should also be ALL.
        Logger loggerRandomOther = LoggerFactory.getLogger("test.random.other");
        Assert.assertTrue(loggerRandomOther.isTraceEnabled());
        Assert.assertTrue(loggerRandomOther.isTraceEnabled(marker));
        Assert.assertTrue(loggerRandomOther.isTraceEnabled(markerOther));
        Assert.assertTrue(loggerRandomOther.isDebugEnabled());
        Assert.assertTrue(loggerRandomOther.isDebugEnabled(marker));
        Assert.assertTrue(loggerRandomOther.isDebugEnabled(markerOther));
        Assert.assertTrue(loggerRandomOther.isInfoEnabled());
        Assert.assertTrue(loggerRandomOther.isInfoEnabled(marker));
        Assert.assertTrue(loggerRandomOther.isInfoEnabled(markerOther));
        Assert.assertTrue(loggerRandomOther.isWarnEnabled());
        Assert.assertTrue(loggerRandomOther.isWarnEnabled(marker));
        Assert.assertTrue(loggerRandomOther.isWarnEnabled(markerOther));
        Assert.assertTrue(loggerRandomOther.isErrorEnabled());
        Assert.assertTrue(loggerRandomOther.isErrorEnabled(marker));
        Assert.assertTrue(loggerRandomOther.isErrorEnabled(markerOther));
    }

    @Test
    public void testLogRecordLoggerName() {
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 3;
        Exception e = new Exception("exception");
        Marker marker = MarkerFactory.getMarker("marker");

        Logger[] loggers = new Logger[] {
            LoggerFactory.getLogger("test.level.all"),
            LoggerFactory.getLogger("test.level.trace"),
            LoggerFactory.getLogger("test.level.debug"),
            LoggerFactory.getLogger("test.level.info"),
            LoggerFactory.getLogger("test.level.warn"),
            LoggerFactory.getLogger("test.level.error"),
            LoggerFactory.getLogger("test.level.off"),
            LoggerFactory.getLogger("test.random"),
            LoggerFactory.getLogger("test.random.other")
        };

        int recordIndex = 0;

        for (Logger logger : loggers) {
            logger.trace("Hello world");
            logger.trace("val={}", i1);
            logger.trace("val={} val={}", i1, i2);
            logger.trace("val={} val={} val={}", i1, i2, i3);
            logger.trace("exception", e);
            logger.trace(marker, "Hello world");
            logger.trace(marker, "val={}", i1);
            logger.trace(marker, "val={} val={}", i1, i2);
            logger.trace(marker, "val={} val={} val={}", i1, i2, i3);
            logger.trace(marker, "exception", e);
            logger.debug("Hello world");
            logger.debug("val={}", i1);
            logger.debug("val={} val={}", i1, i2);
            logger.debug("val={} val={} val={}", i1, i2, i3);
            logger.debug("exception", e);
            logger.debug(marker, "Hello world");
            logger.debug(marker, "val={}", i1);
            logger.debug(marker, "val={} val={}", i1, i2);
            logger.debug(marker, "val={} val={} val={}", i1, i2, i3);
            logger.debug(marker, "exception", e);
            logger.info("Hello world");
            logger.info("val={}", i1);
            logger.info("val={} val={}", i1, i2);
            logger.info("val={} val={} val={}", i1, i2, i3);
            logger.info("exception", e);
            logger.info(marker, "Hello world");
            logger.info(marker, "val={}", i1);
            logger.info(marker, "val={} val={}", i1, i2);
            logger.info(marker, "val={} val={} val={}", i1, i2, i3);
            logger.info(marker, "exception", e);
            logger.warn("Hello world");
            logger.warn("val={}", i1);
            logger.warn("val={} val={}", i1, i2);
            logger.warn("val={} val={} val={}", i1, i2, i3);
            logger.warn("exception", e);
            logger.warn(marker, "Hello world");
            logger.warn(marker, "val={}", i1);
            logger.warn(marker, "val={} val={}", i1, i2);
            logger.warn(marker, "val={} val={} val={}", i1, i2, i3);
            logger.warn(marker, "exception", e);
            logger.error("Hello world");
            logger.error("val={}", i1);
            logger.error("val={} val={}", i1, i2);
            logger.error("val={} val={} val={}", i1, i2, i3);
            logger.error("exception", e);
            logger.error(marker, "Hello world");
            logger.error(marker, "val={}", i1);
            logger.error(marker, "val={} val={}", i1, i2);
            logger.error(marker, "val={} val={} val={}", i1, i2, i3);
            logger.error(marker, "exception", e);

            int numTrace = logger.isTraceEnabled() ? 10 : 0;
            int numDebug = logger.isDebugEnabled() ? 10 : 0;
            int numInfo = logger.isInfoEnabled() ? 10 : 0;
            int numWarn = logger.isWarnEnabled() ? 10 : 0;
            int numError = logger.isErrorEnabled() ? 10 : 0;
            int numTotal = numTrace + numDebug + numInfo + numWarn + numError;

            for (int end = recordIndex + numTotal; recordIndex < end; recordIndex++) {
                assertLoggerName(logger.getName(), recordIndex);
            }
        }
    }

    @Test
    public void testLogRecordSourceClassName() {
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 3;
        Exception e = new Exception("exception");
        Marker marker = MarkerFactory.getMarker("marker");

        Logger[] loggers = new Logger[] {
            LoggerFactory.getLogger("test.level.all"),
            LoggerFactory.getLogger("test.level.trace"),
            LoggerFactory.getLogger("test.level.debug"),
            LoggerFactory.getLogger("test.level.info"),
            LoggerFactory.getLogger("test.level.warn"),
            LoggerFactory.getLogger("test.level.error"),
            LoggerFactory.getLogger("test.level.off"),
            LoggerFactory.getLogger("test.random"),
            LoggerFactory.getLogger("test.random.other")
        };

        int recordIndex = 0;

        for (Logger logger : loggers) {
            logger.trace("Hello world");
            logger.trace("val={}", i1);
            logger.trace("val={} val={}", i1, i2);
            logger.trace("val={} val={} val={}", i1, i2, i3);
            logger.trace("exception", e);
            logger.trace(marker, "Hello world");
            logger.trace(marker, "val={}", i1);
            logger.trace(marker, "val={} val={}", i1, i2);
            logger.trace(marker, "val={} val={} val={}", i1, i2, i3);
            logger.trace(marker, "exception", e);
            logger.debug("Hello world");
            logger.debug("val={}", i1);
            logger.debug("val={} val={}", i1, i2);
            logger.debug("val={} val={} val={}", i1, i2, i3);
            logger.debug("exception", e);
            logger.debug(marker, "Hello world");
            logger.debug(marker, "val={}", i1);
            logger.debug(marker, "val={} val={}", i1, i2);
            logger.debug(marker, "val={} val={} val={}", i1, i2, i3);
            logger.debug(marker, "exception", e);
            logger.info("Hello world");
            logger.info("val={}", i1);
            logger.info("val={} val={}", i1, i2);
            logger.info("val={} val={} val={}", i1, i2, i3);
            logger.info("exception", e);
            logger.info(marker, "Hello world");
            logger.info(marker, "val={}", i1);
            logger.info(marker, "val={} val={}", i1, i2);
            logger.info(marker, "val={} val={} val={}", i1, i2, i3);
            logger.info(marker, "exception", e);
            logger.warn("Hello world");
            logger.warn("val={}", i1);
            logger.warn("val={} val={}", i1, i2);
            logger.warn("val={} val={} val={}", i1, i2, i3);
            logger.warn("exception", e);
            logger.warn(marker, "Hello world");
            logger.warn(marker, "val={}", i1);
            logger.warn(marker, "val={} val={}", i1, i2);
            logger.warn(marker, "val={} val={} val={}", i1, i2, i3);
            logger.warn(marker, "exception", e);
            logger.error("Hello world");
            logger.error("val={}", i1);
            logger.error("val={} val={}", i1, i2);
            logger.error("val={} val={} val={}", i1, i2, i3);
            logger.error("exception", e);
            logger.error(marker, "Hello world");
            logger.error(marker, "val={}", i1);
            logger.error(marker, "val={} val={}", i1, i2);
            logger.error(marker, "val={} val={} val={}", i1, i2, i3);
            logger.error(marker, "exception", e);

            int numTrace = logger.isTraceEnabled() ? 10 : 0;
            int numDebug = logger.isDebugEnabled() ? 10 : 0;
            int numInfo = logger.isInfoEnabled() ? 10 : 0;
            int numWarn = logger.isWarnEnabled() ? 10 : 0;
            int numError = logger.isErrorEnabled() ? 10 : 0;
            int numTotal = numTrace + numDebug + numInfo + numWarn + numError;

            for (int end = recordIndex + numTotal; recordIndex < end; recordIndex++) {
                assertSourceClassName(JDK14LoggerAdapterTest.class.getName(), recordIndex);
            }
        }
    }

    @Test
    public void testLogRecordSourceMethodName1() {
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 3;
        Exception e = new Exception("exception");
        Marker marker = MarkerFactory.getMarker("marker");

        Logger[] loggers = new Logger[] {
            LoggerFactory.getLogger("test.level.all"),
            LoggerFactory.getLogger("test.level.trace"),
            LoggerFactory.getLogger("test.level.debug"),
            LoggerFactory.getLogger("test.level.info"),
            LoggerFactory.getLogger("test.level.warn"),
            LoggerFactory.getLogger("test.level.error"),
            LoggerFactory.getLogger("test.level.off"),
            LoggerFactory.getLogger("test.random"),
            LoggerFactory.getLogger("test.random.other")
        };

        int recordIndex = 0;

        for (Logger logger : loggers) {
            logger.trace("Hello world");
            logger.trace("val={}", i1);
            logger.trace("val={} val={}", i1, i2);
            logger.trace("val={} val={} val={}", i1, i2, i3);
            logger.trace("exception", e);
            logger.trace(marker, "Hello world");
            logger.trace(marker, "val={}", i1);
            logger.trace(marker, "val={} val={}", i1, i2);
            logger.trace(marker, "val={} val={} val={}", i1, i2, i3);
            logger.trace(marker, "exception", e);
            logger.debug("Hello world");
            logger.debug("val={}", i1);
            logger.debug("val={} val={}", i1, i2);
            logger.debug("val={} val={} val={}", i1, i2, i3);
            logger.debug("exception", e);
            logger.debug(marker, "Hello world");
            logger.debug(marker, "val={}", i1);
            logger.debug(marker, "val={} val={}", i1, i2);
            logger.debug(marker, "val={} val={} val={}", i1, i2, i3);
            logger.debug(marker, "exception", e);
            logger.info("Hello world");
            logger.info("val={}", i1);
            logger.info("val={} val={}", i1, i2);
            logger.info("val={} val={} val={}", i1, i2, i3);
            logger.info("exception", e);
            logger.info(marker, "Hello world");
            logger.info(marker, "val={}", i1);
            logger.info(marker, "val={} val={}", i1, i2);
            logger.info(marker, "val={} val={} val={}", i1, i2, i3);
            logger.info(marker, "exception", e);
            logger.warn("Hello world");
            logger.warn("val={}", i1);
            logger.warn("val={} val={}", i1, i2);
            logger.warn("val={} val={} val={}", i1, i2, i3);
            logger.warn("exception", e);
            logger.warn(marker, "Hello world");
            logger.warn(marker, "val={}", i1);
            logger.warn(marker, "val={} val={}", i1, i2);
            logger.warn(marker, "val={} val={} val={}", i1, i2, i3);
            logger.warn(marker, "exception", e);
            logger.error("Hello world");
            logger.error("val={}", i1);
            logger.error("val={} val={}", i1, i2);
            logger.error("val={} val={} val={}", i1, i2, i3);
            logger.error("exception", e);
            logger.error(marker, "Hello world");
            logger.error(marker, "val={}", i1);
            logger.error(marker, "val={} val={}", i1, i2);
            logger.error(marker, "val={} val={} val={}", i1, i2, i3);
            logger.error(marker, "exception", e);

            int numTrace = logger.isTraceEnabled() ? 10 : 0;
            int numDebug = logger.isDebugEnabled() ? 10 : 0;
            int numInfo = logger.isInfoEnabled() ? 10 : 0;
            int numWarn = logger.isWarnEnabled() ? 10 : 0;
            int numError = logger.isErrorEnabled() ? 10 : 0;
            int numTotal = numTrace + numDebug + numInfo + numWarn + numError;

            for (int end = recordIndex + numTotal; recordIndex < end; recordIndex++) {
                assertSourceMethodName("testLogRecordSourceMethodName1", recordIndex);
            }
        }
    }

    @Test
    public void testLogRecordSourceMethodName2() {
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 3;
        Exception e = new Exception("exception");
        Marker marker = MarkerFactory.getMarker("marker");

        Logger[] loggers = new Logger[] {
            LoggerFactory.getLogger("test.level.all"),
            LoggerFactory.getLogger("test.level.trace"),
            LoggerFactory.getLogger("test.level.debug"),
            LoggerFactory.getLogger("test.level.info"),
            LoggerFactory.getLogger("test.level.warn"),
            LoggerFactory.getLogger("test.level.error"),
            LoggerFactory.getLogger("test.level.off"),
            LoggerFactory.getLogger("test.random"),
            LoggerFactory.getLogger("test.random.other")
        };

        int recordIndex = 0;

        for (Logger logger : loggers) {
            logger.trace("Hello world");
            logger.trace("val={}", i1);
            logger.trace("val={} val={}", i1, i2);
            logger.trace("val={} val={} val={}", i1, i2, i3);
            logger.trace("exception", e);
            logger.trace(marker, "Hello world");
            logger.trace(marker, "val={}", i1);
            logger.trace(marker, "val={} val={}", i1, i2);
            logger.trace(marker, "val={} val={} val={}", i1, i2, i3);
            logger.trace(marker, "exception", e);
            logger.debug("Hello world");
            logger.debug("val={}", i1);
            logger.debug("val={} val={}", i1, i2);
            logger.debug("val={} val={} val={}", i1, i2, i3);
            logger.debug("exception", e);
            logger.debug(marker, "Hello world");
            logger.debug(marker, "val={}", i1);
            logger.debug(marker, "val={} val={}", i1, i2);
            logger.debug(marker, "val={} val={} val={}", i1, i2, i3);
            logger.debug(marker, "exception", e);
            logger.info("Hello world");
            logger.info("val={}", i1);
            logger.info("val={} val={}", i1, i2);
            logger.info("val={} val={} val={}", i1, i2, i3);
            logger.info("exception", e);
            logger.info(marker, "Hello world");
            logger.info(marker, "val={}", i1);
            logger.info(marker, "val={} val={}", i1, i2);
            logger.info(marker, "val={} val={} val={}", i1, i2, i3);
            logger.info(marker, "exception", e);
            logger.warn("Hello world");
            logger.warn("val={}", i1);
            logger.warn("val={} val={}", i1, i2);
            logger.warn("val={} val={} val={}", i1, i2, i3);
            logger.warn("exception", e);
            logger.warn(marker, "Hello world");
            logger.warn(marker, "val={}", i1);
            logger.warn(marker, "val={} val={}", i1, i2);
            logger.warn(marker, "val={} val={} val={}", i1, i2, i3);
            logger.warn(marker, "exception", e);
            logger.error("Hello world");
            logger.error("val={}", i1);
            logger.error("val={} val={}", i1, i2);
            logger.error("val={} val={} val={}", i1, i2, i3);
            logger.error("exception", e);
            logger.error(marker, "Hello world");
            logger.error(marker, "val={}", i1);
            logger.error(marker, "val={} val={}", i1, i2);
            logger.error(marker, "val={} val={} val={}", i1, i2, i3);
            logger.error(marker, "exception", e);

            int numTrace = logger.isTraceEnabled() ? 10 : 0;
            int numDebug = logger.isDebugEnabled() ? 10 : 0;
            int numInfo = logger.isInfoEnabled() ? 10 : 0;
            int numWarn = logger.isWarnEnabled() ? 10 : 0;
            int numError = logger.isErrorEnabled() ? 10 : 0;
            int numTotal = numTrace + numDebug + numInfo + numWarn + numError;

            for (int end = recordIndex + numTotal; recordIndex < end; recordIndex++) {
                assertSourceMethodName("testLogRecordSourceMethodName2", recordIndex);
            }
        }
    }

    @Test
    public void testLogRecordLevel() {
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 3;
        Exception e = new Exception("exception");
        Marker marker = MarkerFactory.getMarker("marker");

        Logger logger = LoggerFactory.getLogger("test.level.all");

        int recordIndex = 0;

        logger.trace("Hello world");
        logger.trace("val={}", i1);
        logger.trace("val={} val={}", i1, i2);
        logger.trace("val={} val={} val={}", i1, i2, i3);
        logger.trace("exception", e);
        logger.trace(marker, "Hello world");
        logger.trace(marker, "val={}", i1);
        logger.trace(marker, "val={} val={}", i1, i2);
        logger.trace(marker, "val={} val={} val={}", i1, i2, i3);
        logger.trace(marker, "exception", e);

        int numTrace = 10;
        for (int end = recordIndex + numTrace; recordIndex < end; recordIndex++) {
            assertLevel(Level.FINEST, recordIndex);
        }

        logger.debug("Hello world");
        logger.debug("val={}", i1);
        logger.debug("val={} val={}", i1, i2);
        logger.debug("val={} val={} val={}", i1, i2, i3);
        logger.debug("exception", e);
        logger.debug(marker, "Hello world");
        logger.debug(marker, "val={}", i1);
        logger.debug(marker, "val={} val={}", i1, i2);
        logger.debug(marker, "val={} val={} val={}", i1, i2, i3);
        logger.debug(marker, "exception", e);

        int numDebug = 10;
        for (int end = recordIndex + numDebug; recordIndex < end; recordIndex++) {
            assertLevel(Level.FINE, recordIndex);
        }

        logger.info("Hello world");
        logger.info("val={}", i1);
        logger.info("val={} val={}", i1, i2);
        logger.info("val={} val={} val={}", i1, i2, i3);
        logger.info("exception", e);
        logger.info(marker, "Hello world");
        logger.info(marker, "val={}", i1);
        logger.info(marker, "val={} val={}", i1, i2);
        logger.info(marker, "val={} val={} val={}", i1, i2, i3);
        logger.info(marker, "exception", e);

        int numInfo = 10;
        for (int end = recordIndex + numInfo; recordIndex < end; recordIndex++) {
            assertLevel(Level.INFO, recordIndex);
        }

        logger.warn("Hello world");
        logger.warn("val={}", i1);
        logger.warn("val={} val={}", i1, i2);
        logger.warn("val={} val={} val={}", i1, i2, i3);
        logger.warn("exception", e);
        logger.warn(marker, "Hello world");
        logger.warn(marker, "val={}", i1);
        logger.warn(marker, "val={} val={}", i1, i2);
        logger.warn(marker, "val={} val={} val={}", i1, i2, i3);
        logger.warn(marker, "exception", e);

        int numWarn = 10;
        for (int end = recordIndex + numWarn; recordIndex < end; recordIndex++) {
            assertLevel(Level.WARNING, recordIndex);
        }

        logger.error("Hello world");
        logger.error("val={}", i1);
        logger.error("val={} val={}", i1, i2);
        logger.error("val={} val={} val={}", i1, i2, i3);
        logger.error("exception", e);
        logger.error(marker, "Hello world");
        logger.error(marker, "val={}", i1);
        logger.error(marker, "val={} val={}", i1, i2);
        logger.error(marker, "val={} val={} val={}", i1, i2, i3);
        logger.error(marker, "exception", e);

        int numError = 10;
        for (int end = recordIndex + numError; recordIndex < end; recordIndex++) {
            assertLevel(Level.SEVERE, recordIndex);
        }
    }

    @Test
    public void testTrace() {
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 3;
        Object extra = new Object();
        Exception e = new Exception("This is a test exception.");
        Marker marker = MarkerFactory.getMarker("marker");

        Logger logger = LoggerFactory.getLogger("test.level.all");

        int recordIndex = 0;
        logger.trace("Hello world");
        assertMessage("Hello world", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(null, recordIndex);

        recordIndex++;
        logger.trace("val={}", i1);
        assertMessage("val=1", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1 }, recordIndex);

        recordIndex++;
        logger.trace("val={} val={}", i1, i2);
        assertMessage("val=1 val=2", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1, i2 }, recordIndex);

        recordIndex++;
        logger.trace("val={} val={} val={}", i1, i2, i3);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1, i2, i3 }, recordIndex);

        recordIndex++;
        logger.trace("val={} val={} val={}", i1, i2, i3, extra);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1, i2, i3, extra }, recordIndex);

        recordIndex++;
        logger.trace("val={} val={} val={}", i1, i2, i3, e);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(new Object[] { i1, i2, i3 }, recordIndex);

        recordIndex++;
        logger.trace("val={} val={} val={}", i1, i2, i3, extra, e);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(new Object[] { i1, i2, i3, extra }, recordIndex);

        recordIndex++;
        logger.trace("exception", e);
        assertMessage("exception", recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(null, recordIndex);

        recordIndex++;
        logger.trace(marker, "Hello world");
        assertMessage("Hello world", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(null, recordIndex);

        recordIndex++;
        logger.trace(marker, "val={}", i1);
        assertMessage("val=1", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1 }, recordIndex);

        recordIndex++;
        logger.trace(marker, "val={} val={}", i1, i2);
        assertMessage("val=1 val=2", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1, i2 }, recordIndex);

        recordIndex++;
        logger.trace(marker, "val={} val={} val={}", i1, i2, i3);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1, i2, i3 }, recordIndex);

        recordIndex++;
        logger.trace(marker, "val={} val={} val={}", i1, i2, i3, extra);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1, i2, i3, extra }, recordIndex);

        recordIndex++;
        logger.trace(marker, "val={} val={} val={}", i1, i2, i3, e);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(new Object[] { i1, i2, i3 }, recordIndex);

        recordIndex++;
        logger.trace(marker, "val={} val={} val={}", i1, i2, i3, extra, e);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(new Object[] { i1, i2, i3, extra }, recordIndex);

        recordIndex++;
        logger.trace(marker, "exception", e);
        assertMessage("exception", recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(null, recordIndex);
    }

    @Test
    public void testTraceOff() {
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 3;
        Exception e = new Exception("exception");
        Marker marker = MarkerFactory.getMarker("marker");

        Logger[] loggersOn = new Logger[] {
            LoggerFactory.getLogger("test.level.all"),
            LoggerFactory.getLogger("test.level.trace"),
        };
        Logger[] loggersOff = new Logger[] {
            LoggerFactory.getLogger("test.level.debug"),
            LoggerFactory.getLogger("test.level.info"),
            LoggerFactory.getLogger("test.level.warn"),
            LoggerFactory.getLogger("test.level.error"),
            LoggerFactory.getLogger("test.level.off")
        };

        int recordCount = 0;

        for (Logger logger : loggersOff) {
            logger.trace("Hello world");
            logger.trace("val={}", i1);
            logger.trace("val={} val={}", i1, i2);
            logger.trace("val={} val={} val={}", i1, i2, i3);
            logger.trace("exception", e);
            logger.trace(marker, "Hello world");
            logger.trace(marker, "val={}", i1);
            logger.trace(marker, "val={} val={}", i1, i2);
            logger.trace(marker, "val={} val={} val={}", i1, i2, i3);
            logger.trace(marker, "exception", e);

            // No records should have been handled because the trace level should be
            // disabled for these loggers.
            recordCount += 0;
            Assert.assertEquals(recordCount, listHandler.size());
        }

        for (Logger logger : loggersOn) {
            logger.trace("Hello world");
            logger.trace("val={}", i1);
            logger.trace("val={} val={}", i1, i2);
            logger.trace("val={} val={} val={}", i1, i2, i3);
            logger.trace("exception", e);
            logger.trace(marker, "Hello world");
            logger.trace(marker, "val={}", i1);
            logger.trace(marker, "val={} val={}", i1, i2);
            logger.trace(marker, "val={} val={} val={}", i1, i2, i3);
            logger.trace(marker, "exception", e);

            // All log records should have been handled.
            recordCount += 10;
            Assert.assertEquals(recordCount, listHandler.size());
        }
    }

    @Test
    public void testDebug() {
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 3;
        Object extra = new Object();
        Exception e = new Exception("This is a test exception.");
        Marker marker = MarkerFactory.getMarker("marker");

        Logger logger = LoggerFactory.getLogger("test.level.all");

        int recordIndex = 0;
        logger.debug("Hello world");
        assertMessage("Hello world", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(null, recordIndex);

        recordIndex++;
        logger.debug("val={}", i1);
        assertMessage("val=1", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1 }, recordIndex);

        recordIndex++;
        logger.debug("val={} val={}", i1, i2);
        assertMessage("val=1 val=2", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1, i2 }, recordIndex);

        recordIndex++;
        logger.debug("val={} val={} val={}", i1, i2, i3);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1, i2, i3 }, recordIndex);

        recordIndex++;
        logger.debug("val={} val={} val={}", i1, i2, i3, extra);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1, i2, i3, extra }, recordIndex);

        recordIndex++;
        logger.debug("val={} val={} val={}", i1, i2, i3, e);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(new Object[] { i1, i2, i3 }, recordIndex);

        recordIndex++;
        logger.debug("val={} val={} val={}", i1, i2, i3, extra, e);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(new Object[] { i1, i2, i3, extra }, recordIndex);

        recordIndex++;
        logger.debug("exception", e);
        assertMessage("exception", recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(null, recordIndex);

        recordIndex++;
        logger.debug(marker, "Hello world");
        assertMessage("Hello world", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(null, recordIndex);

        recordIndex++;
        logger.debug(marker, "val={}", i1);
        assertMessage("val=1", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1 }, recordIndex);

        recordIndex++;
        logger.debug(marker, "val={} val={}", i1, i2);
        assertMessage("val=1 val=2", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1, i2 }, recordIndex);

        recordIndex++;
        logger.debug(marker, "val={} val={} val={}", i1, i2, i3);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1, i2, i3 }, recordIndex);

        recordIndex++;
        logger.debug(marker, "val={} val={} val={}", i1, i2, i3, extra);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1, i2, i3, extra }, recordIndex);

        recordIndex++;
        logger.debug(marker, "val={} val={} val={}", i1, i2, i3, e);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(new Object[] { i1, i2, i3 }, recordIndex);

        recordIndex++;
        logger.debug(marker, "val={} val={} val={}", i1, i2, i3, extra, e);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(new Object[] { i1, i2, i3, extra }, recordIndex);

        recordIndex++;
        logger.debug(marker, "exception", e);
        assertMessage("exception", recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(null, recordIndex);
    }

    @Test
    public void testDebugOff() {
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 3;
        Exception e = new Exception("exception");
        Marker marker = MarkerFactory.getMarker("marker");

        Logger[] loggersOn = new Logger[] {
            LoggerFactory.getLogger("test.level.all"),
            LoggerFactory.getLogger("test.level.trace"),
            LoggerFactory.getLogger("test.level.debug"),
        };
        Logger[] loggersOff = new Logger[] {
            LoggerFactory.getLogger("test.level.info"),
            LoggerFactory.getLogger("test.level.warn"),
            LoggerFactory.getLogger("test.level.error"),
            LoggerFactory.getLogger("test.level.off")
        };

        int recordCount = 0;

        for (Logger logger : loggersOff) {
            logger.debug("Hello world");
            logger.debug("val={}", i1);
            logger.debug("val={} val={}", i1, i2);
            logger.debug("val={} val={} val={}", i1, i2, i3);
            logger.debug("exception", e);
            logger.debug(marker, "Hello world");
            logger.debug(marker, "val={}", i1);
            logger.debug(marker, "val={} val={}", i1, i2);
            logger.debug(marker, "val={} val={} val={}", i1, i2, i3);
            logger.debug(marker, "exception", e);

            // No records should have been handled because the debug level should be
            // disabled for these loggers.
            recordCount += 0;
            Assert.assertEquals(recordCount, listHandler.size());
        }

        for (Logger logger : loggersOn) {
            logger.debug("Hello world");
            logger.debug("val={}", i1);
            logger.debug("val={} val={}", i1, i2);
            logger.debug("val={} val={} val={}", i1, i2, i3);
            logger.debug("exception", e);
            logger.debug(marker, "Hello world");
            logger.debug(marker, "val={}", i1);
            logger.debug(marker, "val={} val={}", i1, i2);
            logger.debug(marker, "val={} val={} val={}", i1, i2, i3);
            logger.debug(marker, "exception", e);

            // All log records should have been handled.
            recordCount += 10;
            Assert.assertEquals(recordCount, listHandler.size());
        }
    }

    @Test
    public void testInfo() {
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 3;
        Object extra = new Object();
        Exception e = new Exception("This is a test exception.");
        Marker marker = MarkerFactory.getMarker("marker");

        Logger logger = LoggerFactory.getLogger("test.level.all");

        int recordIndex = 0;
        logger.info("Hello world");
        assertMessage("Hello world", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(null, recordIndex);

        recordIndex++;
        logger.info("val={}", i1);
        assertMessage("val=1", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1 }, recordIndex);

        recordIndex++;
        logger.info("val={} val={}", i1, i2);
        assertMessage("val=1 val=2", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1, i2 }, recordIndex);

        recordIndex++;
        logger.info("val={} val={} val={}", i1, i2, i3);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1, i2, i3 }, recordIndex);

        recordIndex++;
        logger.info("val={} val={} val={}", i1, i2, i3, extra);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1, i2, i3, extra }, recordIndex);

        recordIndex++;
        logger.info("val={} val={} val={}", i1, i2, i3, e);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(new Object[] { i1, i2, i3 }, recordIndex);

        recordIndex++;
        logger.info("val={} val={} val={}", i1, i2, i3, extra, e);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(new Object[] { i1, i2, i3, extra }, recordIndex);

        recordIndex++;
        logger.info("exception", e);
        assertMessage("exception", recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(null, recordIndex);

        recordIndex++;
        logger.info(marker, "Hello world");
        assertMessage("Hello world", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(null, recordIndex);

        recordIndex++;
        logger.info(marker, "val={}", i1);
        assertMessage("val=1", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1 }, recordIndex);

        recordIndex++;
        logger.info(marker, "val={} val={}", i1, i2);
        assertMessage("val=1 val=2", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1, i2 }, recordIndex);

        recordIndex++;
        logger.info(marker, "val={} val={} val={}", i1, i2, i3);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1, i2, i3 }, recordIndex);

        recordIndex++;
        logger.info(marker, "val={} val={} val={}", i1, i2, i3, extra);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1, i2, i3, extra }, recordIndex);

        recordIndex++;
        logger.info(marker, "val={} val={} val={}", i1, i2, i3, e);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(new Object[] { i1, i2, i3 }, recordIndex);

        recordIndex++;
        logger.info(marker, "val={} val={} val={}", i1, i2, i3, extra, e);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(new Object[] { i1, i2, i3, extra }, recordIndex);

        recordIndex++;
        logger.info(marker, "exception", e);
        assertMessage("exception", recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(null, recordIndex);
    }

    @Test
    public void testInfoOff() {
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 3;
        Exception e = new Exception("exception");
        Marker marker = MarkerFactory.getMarker("marker");

        Logger[] loggersOn = new Logger[] {
            LoggerFactory.getLogger("test.level.all"),
            LoggerFactory.getLogger("test.level.trace"),
            LoggerFactory.getLogger("test.level.debug"),
            LoggerFactory.getLogger("test.level.info"),
        };
        Logger[] loggersOff = new Logger[] {
            LoggerFactory.getLogger("test.level.warn"),
            LoggerFactory.getLogger("test.level.error"),
            LoggerFactory.getLogger("test.level.off")
        };

        int recordCount = 0;

        for (Logger logger : loggersOff) {
            logger.info("Hello world");
            logger.info("val={}", i1);
            logger.info("val={} val={}", i1, i2);
            logger.info("val={} val={} val={}", i1, i2, i3);
            logger.info("exception", e);
            logger.info(marker, "Hello world");
            logger.info(marker, "val={}", i1);
            logger.info(marker, "val={} val={}", i1, i2);
            logger.info(marker, "val={} val={} val={}", i1, i2, i3);
            logger.info(marker, "exception", e);

            // No records should have been handled because the info level should be
            // disabled for these loggers.
            recordCount += 0;
            Assert.assertEquals(recordCount, listHandler.size());
        }

        for (Logger logger : loggersOn) {
            logger.info("Hello world");
            logger.info("val={}", i1);
            logger.info("val={} val={}", i1, i2);
            logger.info("val={} val={} val={}", i1, i2, i3);
            logger.info("exception", e);
            logger.info(marker, "Hello world");
            logger.info(marker, "val={}", i1);
            logger.info(marker, "val={} val={}", i1, i2);
            logger.info(marker, "val={} val={} val={}", i1, i2, i3);
            logger.info(marker, "exception", e);

            // All log records should have been handled.
            recordCount += 10;
            Assert.assertEquals(recordCount, listHandler.size());
        }
    }

    @Test
    public void testWarn() {
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 3;
        Object extra = new Object();
        Exception e = new Exception("This is a test exception.");
        Marker marker = MarkerFactory.getMarker("marker");

        Logger logger = LoggerFactory.getLogger("test.level.all");

        int recordIndex = 0;
        logger.warn("Hello world");
        assertMessage("Hello world", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(null, recordIndex);

        recordIndex++;
        logger.warn("val={}", i1);
        assertMessage("val=1", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1 }, recordIndex);

        recordIndex++;
        logger.warn("val={} val={}", i1, i2);
        assertMessage("val=1 val=2", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1, i2 }, recordIndex);

        recordIndex++;
        logger.warn("val={} val={} val={}", i1, i2, i3);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1, i2, i3 }, recordIndex);

        recordIndex++;
        logger.warn("val={} val={} val={}", i1, i2, i3, extra);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1, i2, i3, extra }, recordIndex);

        recordIndex++;
        logger.warn("val={} val={} val={}", i1, i2, i3, e);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(new Object[] { i1, i2, i3 }, recordIndex);

        recordIndex++;
        logger.warn("val={} val={} val={}", i1, i2, i3, extra, e);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(new Object[] { i1, i2, i3, extra }, recordIndex);

        recordIndex++;
        logger.warn("exception", e);
        assertMessage("exception", recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(null, recordIndex);

        recordIndex++;
        logger.warn(marker, "Hello world");
        assertMessage("Hello world", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(null, recordIndex);

        recordIndex++;
        logger.warn(marker, "val={}", i1);
        assertMessage("val=1", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1 }, recordIndex);

        recordIndex++;
        logger.warn(marker, "val={} val={}", i1, i2);
        assertMessage("val=1 val=2", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1, i2 }, recordIndex);

        recordIndex++;
        logger.warn(marker, "val={} val={} val={}", i1, i2, i3);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1, i2, i3 }, recordIndex);

        recordIndex++;
        logger.warn(marker, "val={} val={} val={}", i1, i2, i3, extra);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1, i2, i3, extra }, recordIndex);

        recordIndex++;
        logger.warn(marker, "val={} val={} val={}", i1, i2, i3, e);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(new Object[] { i1, i2, i3 }, recordIndex);

        recordIndex++;
        logger.warn(marker, "val={} val={} val={}", i1, i2, i3, extra, e);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(new Object[] { i1, i2, i3, extra }, recordIndex);

        recordIndex++;
        logger.warn(marker, "exception", e);
        assertMessage("exception", recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(null, recordIndex);
    }

    @Test
    public void testWarnOff() {
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 3;
        Exception e = new Exception("exception");
        Marker marker = MarkerFactory.getMarker("marker");

        Logger[] loggersOn = new Logger[] {
            LoggerFactory.getLogger("test.level.all"),
            LoggerFactory.getLogger("test.level.trace"),
            LoggerFactory.getLogger("test.level.debug"),
            LoggerFactory.getLogger("test.level.info"),
            LoggerFactory.getLogger("test.level.warn"),
        };
        Logger[] loggersOff = new Logger[] {
            LoggerFactory.getLogger("test.level.error"),
            LoggerFactory.getLogger("test.level.off")
        };

        int recordCount = 0;

        for (Logger logger : loggersOff) {
            logger.warn("Hello world");
            logger.warn("val={}", i1);
            logger.warn("val={} val={}", i1, i2);
            logger.warn("val={} val={} val={}", i1, i2, i3);
            logger.warn("exception", e);
            logger.warn(marker, "Hello world");
            logger.warn(marker, "val={}", i1);
            logger.warn(marker, "val={} val={}", i1, i2);
            logger.warn(marker, "val={} val={} val={}", i1, i2, i3);
            logger.warn(marker, "exception", e);

            // No records should have been handled because the trace level should be
            // disabled for these loggers.
            recordCount += 0;
            Assert.assertEquals(recordCount, listHandler.size());
        }

        for (Logger logger : loggersOn) {
            logger.warn("Hello world");
            logger.warn("val={}", i1);
            logger.warn("val={} val={}", i1, i2);
            logger.warn("val={} val={} val={}", i1, i2, i3);
            logger.warn("exception", e);
            logger.warn(marker, "Hello world");
            logger.warn(marker, "val={}", i1);
            logger.warn(marker, "val={} val={}", i1, i2);
            logger.warn(marker, "val={} val={} val={}", i1, i2, i3);
            logger.warn(marker, "exception", e);

            // All log records should have been handled.
            recordCount += 10;
            Assert.assertEquals(recordCount, listHandler.size());
        }
    }

    @Test
    public void testError() {
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 3;
        Object extra = new Object();
        Exception e = new Exception("This is a test exception.");
        Marker marker = MarkerFactory.getMarker("marker");

        Logger logger = LoggerFactory.getLogger("test.level.all");

        int recordIndex = 0;
        logger.error("Hello world");
        assertMessage("Hello world", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(null, recordIndex);

        recordIndex++;
        logger.error("val={}", i1);
        assertMessage("val=1", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1 }, recordIndex);

        recordIndex++;
        logger.error("val={} val={}", i1, i2);
        assertMessage("val=1 val=2", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1, i2 }, recordIndex);

        recordIndex++;
        logger.error("val={} val={} val={}", i1, i2, i3);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1, i2, i3 }, recordIndex);

        recordIndex++;
        logger.error("val={} val={} val={}", i1, i2, i3, extra);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1, i2, i3, extra }, recordIndex);

        recordIndex++;
        logger.error("val={} val={} val={}", i1, i2, i3, e);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(new Object[] { i1, i2, i3 }, recordIndex);

        recordIndex++;
        logger.error("val={} val={} val={}", i1, i2, i3, extra, e);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(new Object[] { i1, i2, i3, extra }, recordIndex);

        recordIndex++;
        logger.error("exception", e);
        assertMessage("exception", recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(null, recordIndex);

        recordIndex++;
        logger.error(marker, "Hello world");
        assertMessage("Hello world", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(null, recordIndex);

        recordIndex++;
        logger.error(marker, "val={}", i1);
        assertMessage("val=1", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1 }, recordIndex);

        recordIndex++;
        logger.error(marker, "val={} val={}", i1, i2);
        assertMessage("val=1 val=2", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1, i2 }, recordIndex);

        recordIndex++;
        logger.error(marker, "val={} val={} val={}", i1, i2, i3);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1, i2, i3 }, recordIndex);

        recordIndex++;
        logger.error(marker, "val={} val={} val={}", i1, i2, i3, extra);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1, i2, i3, extra }, recordIndex);

        recordIndex++;
        logger.error(marker, "val={} val={} val={}", i1, i2, i3, e);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(new Object[] { i1, i2, i3 }, recordIndex);

        recordIndex++;
        logger.error(marker, "val={} val={} val={}", i1, i2, i3, extra, e);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(new Object[] { i1, i2, i3, extra }, recordIndex);

        recordIndex++;
        logger.error(marker, "exception", e);
        assertMessage("exception", recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(null, recordIndex);
    }

    @Test
    public void testErrorOff() {
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 3;
        Exception e = new Exception("exception");
        Marker marker = MarkerFactory.getMarker("marker");

        Logger[] loggersOn = new Logger[] {
            LoggerFactory.getLogger("test.level.all"),
            LoggerFactory.getLogger("test.level.trace"),
            LoggerFactory.getLogger("test.level.debug"),
            LoggerFactory.getLogger("test.level.info"),
            LoggerFactory.getLogger("test.level.warn"),
            LoggerFactory.getLogger("test.level.error"),
        };
        Logger[] loggersOff = new Logger[] {
            LoggerFactory.getLogger("test.level.off")
        };

        int recordCount = 0;

        for (Logger logger : loggersOff) {
            logger.error("Hello world");
            logger.error("val={}", i1);
            logger.error("val={} val={}", i1, i2);
            logger.error("val={} val={} val={}", i1, i2, i3);
            logger.error("exception", e);
            logger.error(marker, "Hello world");
            logger.error(marker, "val={}", i1);
            logger.error(marker, "val={} val={}", i1, i2);
            logger.error(marker, "val={} val={} val={}", i1, i2, i3);
            logger.error(marker, "exception", e);

            // No records should have been handled because the trace level should be
            // disabled for these loggers.
            recordCount += 0;
            Assert.assertEquals(recordCount, listHandler.size());
        }

        for (Logger logger : loggersOn) {
            logger.error("Hello world");
            logger.error("val={}", i1);
            logger.error("val={} val={}", i1, i2);
            logger.error("val={} val={} val={}", i1, i2, i3);
            logger.error("exception", e);
            logger.error(marker, "Hello world");
            logger.error(marker, "val={}", i1);
            logger.error(marker, "val={} val={}", i1, i2);
            logger.error(marker, "val={} val={} val={}", i1, i2, i3);
            logger.error(marker, "exception", e);

            // All log records should have been handled.
            recordCount += 10;
            Assert.assertEquals(recordCount, listHandler.size());
        }
    }

    @Test
    public void testLocationAware() {
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 3;
        Object extra = new Object();
        Exception e = new Exception("This is a test exception.");
        Marker marker = MarkerFactory.getMarker("marker");

        Logger plainLogger = LoggerFactory.getLogger("test.level.all");
        Assert.assertTrue(plainLogger instanceof LocationAwareLogger);
        LocationAwareLogger logger = (LocationAwareLogger) plainLogger;

        String fqcn = JDK14LoggerAdapter.class.getName();
        String className = JDK14LoggerAdapterTest.class.getName();
        String methodName = "testLocationAware";

        // This unit test method is called via reflection.
        String fqcnOther = JDK14LoggerAdapterTest.class.getName();
        String classNameOther = "sun.reflect.NativeMethodAccessorImpl";
        String methodNameOther = "invoke0";

        // Perform some sparse testing of different combinations of parameters

        try {
            logger.log(null, fqcn, -1, "Hello world", null, null);
            Assert.fail();
        } catch (IllegalStateException ignored) {
        }

        int recordIndex = 0;
        logger.log(null, fqcn, LocationAwareLogger.TRACE_INT, "Hello world", null, null);
        assertLevel(Level.FINEST, recordIndex);
        assertMessage("Hello world", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(null, recordIndex);
        assertSourceClassName(className, recordIndex);
        assertSourceMethodName(methodName, recordIndex);

        recordIndex++;
        logger.log(null, fqcnOther, LocationAwareLogger.DEBUG_INT, "val=1", new Object[] { i1 }, null);
        assertLevel(Level.FINE, recordIndex);
        assertMessage("val=1", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1 }, recordIndex);
        assertSourceClassName(classNameOther, recordIndex);
        assertSourceMethodName(methodNameOther, recordIndex);

        recordIndex++;
        logger.log(null, fqcn, LocationAwareLogger.INFO_INT, "val=1 val=2 val=3", new Object[] { i1, i2, i3, extra }, null);
        assertLevel(Level.INFO, recordIndex);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1, i2, i3, extra }, recordIndex);
        assertSourceClassName(className, recordIndex);
        assertSourceMethodName(methodName, recordIndex);

        recordIndex++;
        logger.log(null, fqcn, LocationAwareLogger.WARN_INT, null, new Object[] { i1, i2 }, e);
        assertLevel(Level.WARNING, recordIndex);
        assertMessage(null, recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(new Object[] { i1, i2 }, recordIndex);
        assertSourceClassName(className, recordIndex);
        assertSourceMethodName(methodName, recordIndex);

        recordIndex++;
        logger.log(null, fqcn, LocationAwareLogger.ERROR_INT, "exception", null, e);
        assertLevel(Level.SEVERE, recordIndex);
        assertMessage("exception", recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(null, recordIndex);
        assertSourceClassName(className, recordIndex);
        assertSourceMethodName(methodName, recordIndex);

        recordIndex++;
        logger.log(marker, fqcnOther, LocationAwareLogger.TRACE_INT, "Hello world", null, null);
        assertLevel(Level.FINEST, recordIndex);
        assertMessage("Hello world", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(null, recordIndex);
        assertSourceClassName(classNameOther, recordIndex);
        assertSourceMethodName(methodNameOther, recordIndex);

        recordIndex++;
        logger.log(marker, fqcn, LocationAwareLogger.DEBUG_INT, "val=1", new Object[] { i1 }, null);
        assertLevel(Level.FINE, recordIndex);
        assertMessage("val=1", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1 }, recordIndex);
        assertSourceClassName(className, recordIndex);
        assertSourceMethodName(methodName, recordIndex);

        recordIndex++;
        logger.log(marker, fqcnOther, LocationAwareLogger.INFO_INT, "val=1 val=2 val=3", new Object[] { i1, i2, i3, extra }, null);
        assertLevel(Level.INFO, recordIndex);
        assertMessage("val=1 val=2 val=3", recordIndex);
        assertThrown(null, recordIndex);
        assertParameters(new Object[] { i1, i2, i3, extra }, recordIndex);
        assertSourceClassName(classNameOther, recordIndex);
        assertSourceMethodName(methodNameOther, recordIndex);

        recordIndex++;
        logger.log(marker, fqcnOther, LocationAwareLogger.WARN_INT, null, new Object[] { i1, i2 }, e);
        assertLevel(Level.WARNING, recordIndex);
        assertMessage(null, recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(new Object[] { i1, i2 }, recordIndex);
        assertSourceClassName(classNameOther, recordIndex);
        assertSourceMethodName(methodNameOther, recordIndex);

        recordIndex++;
        logger.log(marker, fqcn, LocationAwareLogger.ERROR_INT, "exception", null, e);
        assertLevel(Level.SEVERE, recordIndex);
        assertMessage("exception", recordIndex);
        assertThrown(e, recordIndex);
        assertParameters(null, recordIndex);
        assertSourceClassName(className, recordIndex);
        assertSourceMethodName(methodName, recordIndex);
    }

    private void assertMessage(String expected, int index) {
        LogRecord logRecord = listHandler.getRecord(index);
        Assert.assertNotNull(logRecord);
        Assert.assertEquals(expected, logRecord.getMessage());
    }

    private void assertLevel(Level level, int index) {
        LogRecord logRecord = listHandler.getRecord(index);
        Assert.assertNotNull(logRecord);
        Assert.assertEquals(level, logRecord.getLevel());
    }

    private void assertParameters(Object[] parameters, int index) {
        LogRecord logRecord = listHandler.getRecord(index);
        Assert.assertNotNull(logRecord);
        Assert.assertArrayEquals(parameters, logRecord.getParameters());
    }

    private void assertThrown(Throwable exceptionType, int index) {
        LogRecord logRecord = listHandler.getRecord(index);
        Assert.assertNotNull(logRecord);
        Assert.assertEquals(exceptionType, logRecord.getThrown());
    }

    private void assertLoggerName(String name, int index) {
        LogRecord logRecord = listHandler.getRecord(index);
        Assert.assertNotNull(logRecord);
        Assert.assertEquals(name, logRecord.getLoggerName());
    }

    private void assertSourceClassName(String name, int index) {
        LogRecord logRecord = listHandler.getRecord(index);
        Assert.assertNotNull(logRecord);
        Assert.assertEquals(name, logRecord.getSourceClassName());
    }

    private void assertSourceMethodName(String name, int index) {
        LogRecord logRecord = listHandler.getRecord(index);
        Assert.assertNotNull(logRecord);
        Assert.assertEquals(name, logRecord.getSourceMethodName());
    }

    private static class ListHandler extends java.util.logging.Handler {

        private final List<LogRecord> recordList = new ArrayList<LogRecord>();

        public LogRecord getRecord(int index) {
            return recordList.get(index);
        }

        public int size() {
            return recordList.size();
        }

        @Override
        public void publish(LogRecord record) {
            recordList.add(record);
        }

        @Override
        public void flush() {
        }

        @Override
        public void close() throws SecurityException {
        }
    }
}
