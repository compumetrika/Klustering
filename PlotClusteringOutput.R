## -----------------------------------------------------------------------------
## Plot.Clustering.Output.R
##
## This just plots the clustering output from my clustering algorithm.
##
##
## -----------------------------------------------------------------------------

cluster.data <- read.csv("testClustering.csv")
summary(cluster.data)

clusters <- unique(cluster.data$cluster)
K <- length(clusters)

colors <- rainbow(n=K)
styles <- clusters                       ## this will work as long as these are numbers - I think.

clusterList <- list()

clusterID <- clusters[1]
clusterIndex <- (cluster.data$cluster == clusterID) & (cluster.data$isCentroid == 0)
x <- cluster.data$x[clusterIndex]
y <- cluster.data$y[clusterIndex]
clusterCenterIndex <- (cluster.data$cluster == clusterID) & (cluster.data$isCentroid == 1)
xcenter <- cluster.data$x[clusterCenterIndex]
ycenter <- cluster.data$y[clusterCenterIndex]


yrange <- range(cluster.data$y[cluster.data$isCentroid == 0])
xrange <- range(cluster.data$x[cluster.data$isCentroid == 0])

plot(x=x, y=y, main="Data, Clustered", col=colors[1], pch=styles[1], ylim=yrange, xlim=xrange)
points(x=xcenter, y=ycenter, col=colors[1], pch="X")

if (K > 1) {
  colors <- colors [-1]
  clusters <- clusters[-1]
  styles <- styles[-1]
    
  for (i in c(1:(K-1))) {
    clusterID <- clusters[i]
    clusterIndex <- cluster.data$cluster == clusterID
    x <- cluster.data$x[clusterIndex]
    y <- cluster.data$y[clusterIndex]

    clusterCenterIndex <- (cluster.data$cluster == clusterID) & (cluster.data$isCentroid == 1)
    xcenter <- cluster.data$x[clusterCenterIndex]
    ycenter <- cluster.data$y[clusterCenterIndex]

    points(x=x, y=y, col=colors[i], pch=styles[i])
    points(x=xcenter, y=ycenter, col=colors[i], pch="X")
  }
}







