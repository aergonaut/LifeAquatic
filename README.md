# Life Aquatic

[![Build Status](https://travis-ci.org/aergonaut/LifeAquatic.svg?branch=master)](https://travis-ci.org/aergonaut/LifeAquatic)

**Life Aquatic** is a Minecraft mod that adds various gameplay elements focused 
around underwater and aquatic play.

This is still under heavy development and this is my first foray into mod 
development. _Definitely_ not ready to be used on a server unless you are
willing to accept the risks!

Features wishlist:

- [ ] underwater villages
- [ ] underwater villagers (merpeople)
- [ ] aquatic passive mobs (whales, seals, electric eels)
- [ ] aquatic hostile mobs (sharks, flesh-eating jellyfish)
- [ ] aquatic bosses (Kraken, Leviathan)

## Contributing

This mod is open source! If you have an idea for a feature you would like to
see, please feel free to submit a pull request!

1. Fork the repo
2. Create a branch
3. Push your branch to GitHub
4. Open a Pull Request!

### Dev environment setup

1. Choose your editor. Eclipse and IntelliJ are supported.
2. Download the deobfuscated Minecraft sources: `./gradlew setupDecompWorkspace`
3. Generate project files. If using Eclipse: `./gradlew eclipse`; if using
IntelliJ: `./gradlew idea`

### Style

Coding style is enforced by [ScalaStyle](http://www.scalastyle.org/). ScalaStyle
is run against every commit as part of each [build](https://travis-ci.org/aergonaut/LifeAquatic).

To check style yourself, run `./gradlew scalaStye`.

## License

The MIT License (MIT)

Copyright (c) 2015 Chris Fung

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
