#!/bin/bash
echo '------------------------№1-------------------------'
mkdir lab0
chmod 777 lab0
cd lab0/
mkdir cranidos6 crawdaunt6 croconaw5
cd cranidos6/
mkdir seismitoad slowbro weedle meditite
cd ..
cd crawdaunt6/
mkdir vanillite ferroseed muk octillery
cd ..
cd croconaw5/
mkdir rapidash manectric
touch aron voltorb chikorita
cd ..
touch carracosta4 frillish3 pansear7
echo 'Ходы Aqua Tail Block Earth Power Icy Wind Iron Defense\nIron Head Iron Tail Low Kick Sleep Talk Snore Stealth Rock\nSuperpower' > carracosta4
cd croconaw5/
echo 'satk=4 sdef=4 spd=3' > aron
echo 'Ходы Foul Play Magic\nCoat Magnet Rise Role Play Rollout Shock Wave Signal Beam Sleep Talk\nSnore Sucker Punch Swift' > voltorb
echo 'Живет Forest\nGrassland' > chikorita
cd ..
echo 'Ходы Bind Dark Pulse Giga Drain Icy Wind Magic\nCoat Pain Split Sleep Talk Snore Spite Trick' > frillish3
echo 'Возможности\nOverland=6 Surface=4 Jump=4 Power=2 Intelligence=4' > pansear7
echo '------------------------№2-------------------------'
chmod 062 carracosta4
chmod 573 cranidos6/
cd cranidos6/
chmod 777 seismitoad/
chmod 770 slowbro/
chmod 555 weedle/
chmod 577 meditite
cd ..
chmod 361 crawdaunt6/
cd crawdaunt6/
chmod 753 vanillite/
chmod 750 ferroseed/
chmod 373 muk/
chmod 373 octillery/
cd ..
chmod 573 croconaw5
cd croconaw5/
chmod 600 aron
chmod 513 rapidash
chmod 573 manectric
chmod 004 voltorb
chmod 400 chikorita
cd ..
chmod 400 frillish3
chmod 004 pansear7
echo '------------------------№3-------------------------'
cat croconaw5/chikorita croconaw5/chikorita > frillish3_10
chmod u+w croconaw5
ln frillish3 croconaw5/aronfrillish
chmod u-w croconaw5
chmod u+w cranidos6 cranidos6/weedle
chmod u+r carracosta4
cp carracosta4 cranidos6/weedle
chmod u-w cranidos6 cranidos6/weedle
chmod u-r carracosta4
chmod u+w croconaw5
ln -s ../frillish3 croconaw5/chikoritafrillish
ln -s croconaw5 Copy_90
chmod u+r carracosta4
cat carracosta4 > croconaw5/voltorbcarracosta
chmod u-r carracosta4
chmod u+w croconaw5/manectric
chmod u+r crawdaunt6 crawdaunt6/muk crawdaunt6/octillery
cp -r crawdaunt6 croconaw5/manectric
chmod u-r crawdaunt6 crawdaunt6/muk crawdaunt6/octillery
chmod u-w croconaw5 croconaw5/manectric
echo '------------------------№4-------------------------'
echo '-----------------------№4.1------------------------'
wc -m f* */f* */*/f* | sort
echo '-----------------------№4.2------------------------'
ls cranidos6 2>/dev/null | sort
echo '-----------------------№4.3------------------------'
cat *6 */*6 */*/*6 | sort -r
echo '-----------------------№4.4------------------------'
ls -lRur 2>/dev/null | grep ^- | grep " m" | head -n2
echo '-----------------------№4.5------------------------'
ls -lRS 2>/dev/null | grep ^- | grep " f"
echo '----------------------№4.6.1-----------------------'
ls -lRS 2>/tmp/errors | grep ^- | grep 'se' | head -n 2
echo '----------------------№4.6.2-----------------------'
ls -R 2>/tmp/errors | xargs grep -l 'se' 2>/tmp/errors | xargs ls -lS 2>/tmp/errors
echo '----------------------№4.6.3-----------------------'
for file in $(grep -rl 'se' . 2>/tmp/errors); do du -b "$file"; done | sort -n | head -n 2 2>/tmp/errors
echo '------------------------№5-------------------------'
chmod -R u+w croconaw5 carracosta4
rm carracosta4
rm croconaw5/aron
rm Copy*
rm croconaw5/aronfrilli*
rm -r croconaw5/manectric
rm -r croconaw5

