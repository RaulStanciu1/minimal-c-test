#include "___mct___.h"
#include "___mct_common_src___.h"

#define MCT_TESTS_N     ___MCT__TEST__N___

___MCT__ADD__SRC__FILES__INCLUDES___

___MCT__ADD_ENV__FILES__INCLUDES___

___MCT__ADD__TEST__FILES__INCLUDES___




int main(void)
{
    for(int i = 0; i < MCT_TESTS_N; i++)
    {
        __MCT__TEST_RUN__(i);
    }
    return 0;
}
