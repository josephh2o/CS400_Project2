# List any changes that should be made to get stuff working #

### for MovieRedBlackTree.java ###

- [ ] things are returning null when items are not found; these cause NullPointerExceptions in Backend
	(instead, just return empty lists)
- [ ] getting a never ending loop around "man with the golden gun"

### Frontend ###

- [x] when returning search results, have all information for the movies display on console
- [x] we should address situations where the user calls functions before the data loads

### Backend or Algo ###

- [ ] there are duplicates so the current technique of having each movie be its own list isn't working
but also, what if we just deleted the duplicates?