## -----------------------------------------------------------------------------
## MakeSomeData.R
##
## This just creates some data.
##
## -----------------------------------------------------------------------------


filename <- "test1.csv"
N <- 100

x1 <- runif(50, 1,12)
y1 <- runif(50, 1,12)

x2 <- runif(50, 7,20)
y2 <- runif(50, 7,20)

xrange <- range(c(x1, x2))
yrange <- range(c(y1, y2))

plot(x=x1, y=y1, main="Data, Clustered", col=1, ylim=yrange, xlim=xrange)
points(x=x2, y=y2, col=2)

X <- c(x1, x2)
Y <- c(y1, y2)

write.csv(cbind(X,Y), filename, row.names=FALSE)
