#include <iostream>
#include <algorithm>
#include <string>
#include <vector>

#define pb push_back
#define all(x) x.begin(), x.end()

using namespace std;
using ll = long long;
using ld = long double;


vector<string> split(string& s, const string& delim)
{
    vector<string> result;
    int prev = 0, cur = 0;
    do {
        cur = s.find(delim, prev);
        if (cur == string::npos) cur = s.length();
        string text = s.substr(prev, cur-prev);
        result.pb(text);
        prev = cur + delim.length();
    } while (prev < s.length() && cur < s.length());
    return result;
}


string delete_zeros(const string &s) {
    string res = "";
    int n = s.length();
    for (int i = 0; i < n; i++) {
        if (res.empty() && s[i] == '0') continue;
        res += s[i];
    }
    if (res.empty()) res = "0"; 
    return res;
}


string add_zeros(const string &s, int pow) {
    string res = string(pow - s.length(), '0') + s;
    return res;
}


vector<string> batch(string& s, int cnt) {
    vector<string> res;
    string cur = "";
    for (int i = 0; i < s.length(); i++) {
        if (i % cnt == 0 && i) {
            res.pb(cur);
            cur.clear();
        }
        cur += s[i];
    }
    if (!cur.empty()) res.pb(cur);
    return res;
}


const long double EPS = 1e-5;


struct Translator {
private:
   vector<ll> Fib;

    void build() {
        Fib[0] = 1;
        Fib[1] = 2;
        for (int i = 2; i < 32; i++) {
            Fib[i] = Fib[i - 1] + Fib[i - 2];
        }
    }

public:
    Translator(): Fib(32) {
        build();
    }

    ld ToDec(string &x, const ll cur_base) {  // Перевод числа из заданно в десятичную
        vector<string> splitted = split(x, ".");
        int frac_len = 0;
        if (splitted.size() > 1) {
            frac_len = splitted[1].length();
        }
        reverse(all(x));
        ld res = 0;
        ld mult = 1;
        for (int i = 0; i < frac_len; i++) {
            mult /= cur_base;
        }
        for (char c_dig : x) {
            if (c_dig == '.') continue;
            ll dig = (c_dig > '9' ? c_dig - 55:c_dig - '0');
            res += dig * mult;
            mult *= cur_base;
        }
        return res;
    }


    string ToBase(string &x, const ll cur_base, const ll wanted_base) {  // Перевод из одной в другую
        string good = delete_zeros(x);
        ld dec_x = ToDec(good, cur_base);
        ll dec_z = (ll)dec_x;
        ll nach_z = dec_z;
        string res = "";
        while (dec_z) {
            ll r = dec_z % wanted_base;
            r += (r > 9 ? 55:'0');
            res += r; 
            dec_z /= wanted_base;
        }
        if (res == "") {
            res = "0";
        }
        reverse(all(res));
        ld frac = dec_x - nach_z;
        if (frac > EPS) {
            res += '.';
        }
        while (frac > EPS) {
            frac *= wanted_base;
            ll z = (ll)frac;
            frac -= z;
            z += (z > 9 ? 55:'0');
            res += char(z);
            if (split(res, ".")[1].length() >= 5) break; 
        }
        return res;
    }

    string BinToKBin(string &s, int wanted_base) {  // Из двоичной в 2^k-ичную
        int pow = __builtin_ctz(wanted_base);
        vector<string> splt = split(s, ".");
        string z_s = "", f_s = "";
        z_s = splt[0];
        if (splt.size() > 1) {
            f_s = splt[1];
        } 
        int zr_nmb = (pow - (z_s.length() % pow)) % pow;
        z_s = string(zr_nmb, '0') + z_s;
        zr_nmb = (pow - (f_s.length() % pow)) % pow;
        f_s = f_s + string(zr_nmb, '0');
        vector<string> z_part = batch(z_s, pow);
        vector<string> f_part = batch(f_s, pow);
        string res = "";
        for (string p: z_part) {
            res += ToBase(p, 2, wanted_base);
        }
        if (res == "") {
            res = "0";
        }
        if (splt.size() > 1) {
            res += '.';
        }
        for (string p: f_part) {
            res += ToBase(p, 2, wanted_base);
        }
        return res;
    }

    string KBinToBin(string &s, int cur_base) {  // Из 2^k-ичной в двоичную
        int pow = __builtin_ctz(cur_base);
        vector<string> splt = split(s, ".");
        string z_s = "", f_s = "";
        z_s = splt[0];
        if (splt.size() > 1) {
            f_s = splt[1];
        }
        string res = "";
        for (char c : z_s) {
            string dig = string(1, c);
            res += add_zeros(ToBase(dig, cur_base, 2), pow);
        }
        if (res == "") {
            res = "0";
        }
        if (splt.size() > 1) {
            res += '.';
        }
        for (char c : f_s) {
            string dig = string(1, c);
            res += add_zeros(ToBase(dig, cur_base, 2), pow);
        }
        string ans = delete_zeros(res);
        reverse(all(ans));
        ans = delete_zeros(ans);
        reverse(all(ans));
        if (ans[0] == '.') ans = '0' + ans;
        return ans;
    }

    string PosToNeg(string x, const ll cur_base, ll want_base) {
        ll val = (ll)ToDec(x, cur_base);
        ll r;
        string res = "";
        do {
            r = val % want_base;
            val /= want_base;
            if (r < 0) {
                r += abs(want_base);
                val++;
            }
            res = char(r + (r > 9 ? 55:'0')) + res;
        } while (val != 0);
        return res;
    }

    string NegToPos(string &x, const ll cur_base, ll want_base) {
        ll multi = 1;
        ll res = 0;
        reverse(all(x));
        for (char c : x) {
            res += multi*(c > '9' ? c-55:c-'0');
            multi *= cur_base;
        }
        string ans = to_string(res);
        return ToBase(ans, 10, want_base);
    }

    string ToFib(ll x) {
        //ll x = stoi(numb);
        string str = "";
        int j = 31;
        while (x) {
            if (x / Fib[j] == 1) str += '1';
            else if (!str.empty()) str += '0';
            x %= Fib[j];
            j--;
        }
        while (j >= 0) {
            str += '0';
            j--;
        }
        return str;
    }

    ll FibToDec(string x) {
        ll ans = 0;
        reverse(all(x));
        for (int i = 0; i < x.length(); i++) {
            if (x[i] - '0') {
                ans += Fib[i];
            }
        }
        return ans;
    } 
};


int main() {
    //freopen("input.txt", "r", stdin);
    //freopen("output.txt", "w", stdout);
    Translator T = Translator();
    return 0;
}