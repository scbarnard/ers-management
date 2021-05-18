package ers.util;

import org.apache.logging.log4j.*;

/**
 * Wrapper Interface for Logging
 */
public interface Logger {
    org.apache.logging.log4j.Logger logger = LogManager.getRootLogger();
}
