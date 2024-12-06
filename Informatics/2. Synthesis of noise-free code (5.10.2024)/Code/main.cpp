#include <iostream>
#include <string>

int log2(const int n) {
    int ans = 0;
    for (int i = 0; i < 31; i++) {
        if ((1 << i) & n) ans = i;
    }
    return ans;
}

std::string end(const int n) {
    int r = n % 10;
    std::string ans;
    switch (r) {
        case 1:ans = "st";
        break;
        case 2:ans = "nd";
        break;
        case 3:ans = "rd";
        break;
        default:ans = "th";
    }
    if (n / 10 % 10 == 1) {
        ans = "th";
    }
    return ans;
}

int main() {
    std::string code;
    std::cin >> code;
    int n = (int) code.length();
    int pow = log2(n);
    int err = 0;
    for (int i = pow; i >= 0; i--) {
        int cnt = 0;
        int s = 0;
        for (int j = (1 << i) - 1; j < n; j++) {
            s ^= code[j] - '0';
            if (++cnt == (1 << i)) j += cnt, cnt = 0;
        }
        err <<= 1;
        err += s;
    }
    if (err) {
        code[err - 1] ^= 1;
        std::cout << code << "\nTransmitted code is incorrect. Error in " << err << end(err) << " bit.";
    } else {
        std::cout << code << "\nTransmitted code is correct.";
    }
}