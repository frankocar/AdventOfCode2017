#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void solve(int* values, int len, int step2) {
    int loc = 0;
    int cnt = 0;
    int old_loc;

    while (loc < len) {
        old_loc = loc;
        loc += values[loc];
        if (step2 && values[old_loc] >= 3) {
            values[old_loc]--;
        } else {
            values[old_loc]++;
        }

        cnt++;
    }

    printf("%d\n", cnt);
}

int* arrcpy(int const * src, int len) {
     int * p = malloc(len * sizeof(int));
     memcpy(p, src, len * sizeof(int));
     return p;
}

int main(void) {
    int lines_allocated = 1200;
    int max_len = 12;
    char str[12];
    int *values = (int*)malloc(sizeof(int) * lines_allocated);
    if (values == NULL) {
        printf("Out of memory\n");
        exit(1);
    }

    FILE *fp = fopen("day5_input.txt", "r");
    if (fp == NULL) {
        printf("Can't open file\n");
        exit(1);
    }

    int i;
    for (i = 0; 1; i++) {
        if (i > lines_allocated) {
            int newSize;
            newSize = lines_allocated * 2;
            values = (int*)realloc(values, sizeof(int) * newSize);
            if (values == NULL) {
                    printf("Out of memory\n");
                    exit(1);
            }
            lines_allocated = newSize;
        }

        if (fgets(str, max_len - 1, fp) == NULL) {
            break;
        }

        values[i] = atoi(str);
    }

    solve(arrcpy(values, i), i, 0);
    solve(arrcpy(values, i), i, 1);

    return 0;
}