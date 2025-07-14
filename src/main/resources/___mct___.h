#ifndef ___MCT___H
#define ___MCT___H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

#define const

FILE* ___mct_fptr___;

static inline int ___mct_setup(char* file)
{
    return ((___mct_fptr___ = fopen(file, "w")) == NULL) ? -1 : 0;
}

static inline void ___mct_cleanup()
{
    free(___mct_fptr___);
    ___mct_fptr___ = NULL;
}



#define MCT_ASSERT_EQ(ACTUAL, EXPECTED) fprintf(___mct_fptr___, "%s %d %g %g\n", #ACTUAL, ACTUAL == EXPECTED, (double)EXPECTED, (double)ACTUAL)
#define MCT_ASSERT_EQ_DELTA(ACTUAL, EXPECTED, DELTA) fprintf(___mct_fptr___, "%s %d %g %g\n", #ACTUAL, fabs((double)ACTUAL - (double)EXPECTED) < DELTA, (double)EXPECTED, (double)ACTUAL)
#define MCT_ASSERT_EQ_STR(ACTUAL, EXPECTED) fprintf(___mct_fptr___, "%s %d %s %s\n", #ACTUAL, strcmp(ACTUAL, EXPECTED) == 0, EXPECTED, ACTUAL)

#define MCT_STEP_CHECKPOINT_BEGIN(STEP) fprintf(___mct_fptr___, "MCT_BEGIN_STEP %d\n", STEP)
#define MCT_STEP_CHECKPOINT_END(STEP) fprintf(___mct_fptr___, "MCT_END_STEP %d\n", STEP)



#endif