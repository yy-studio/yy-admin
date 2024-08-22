/*
 * 错误上报sentry
 *
 * @Author:    yy-studio
 * @Date:      2024-01-02 20:49:28
 * 
 * 
 * @Copyright  yy-studio （
 */

export const smartSentry = {
    /**
     * sentry 主动上报
     */
    captureError: (error) => {
        if (error.config && error.data && error && error.headers && error.request && error.status) {
            return;
        }
        // Sentry.captureException(error);
        console.error(error);
    },
};
