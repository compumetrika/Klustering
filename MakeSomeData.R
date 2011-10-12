## -----------------------------------------------------------------------------
## MakeSomeData.R
##
## This just creates some data.
##
## -----------------------------------------------------------------------------


filename <- "test1.csv"
N <- 100

x1 <- runif(50, 8,12)
y1 <- runif(50, 8,12)

x2 <- runif(50, 16,20)
y2 <- runif(50, 5,10)

x3 <- runif(50, 12,16)
y3 <- runif(50, 19,23)



# x1 <- runif(50, 10,12)
# y1 <- runif(50, 1,12)
# 
# x2 <- runif(50, 7,20)
# y2 <- runif(50, 20,22)
# 
# x3 <- runif(50, 15,20)
# y3 <- runif(50, 10,16)



xrange <- range(c(x1, x2, x3))
yrange <- range(c(y1, y2, y3))

plot(x=x1, y=y1, main="Data, Clustered", col=1, ylim=yrange, xlim=xrange)
points(x=x2, y=y2, col=2)
points(x=x3, y=y3, col=3)


X <- c(x1, x2, x3)
Y <- c(y1, y2, y3)

write.csv(cbind(X,Y), filename, row.names=FALSE)
