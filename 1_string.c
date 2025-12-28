#include <stdio.h>

int main() {
    char str[] = "hello world";
    char result[50];
    
    for (int i = 0; str[i] != '\0'; i++) {
        result[i] = str[i] ^ 0;   // XOR with 0
    }
    
    // Null-terminate the result string
    result[sizeof("hello world") - 1] = '\0';

    printf("Original String: %s\n", str);
    printf("After XOR with 0: %s\n", result);

    return 0;
}
