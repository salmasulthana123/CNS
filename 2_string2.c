#include <stdio.h>

int main() {
    char str[] = "hellow world";
    char xor_result[50];
    char and_result[50];

    // XOR each character with 127
    for (int i = 0; str[i] != '\0'; i++) {
        xor_result[i] = str[i] ^ 127;
    }
    xor_result[sizeof(str) - 1] = '\0';

    // AND each character with 127
    for (int i = 0; str[i] != '\0'; i++) {
        and_result[i] = str[i] & 127;
    }
    and_result[sizeof(str) - 1] = '\0';

    printf("Original String : %s\n", str);
    printf("XOR with 127   : %s\n", xor_result);
    printf("AND with 127   : %s\n", and_result);

    return 0;
}
