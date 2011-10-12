## -----------------------------------------------------------------------------
## Plot.Clustering.Output.R
##
## This just plots the clustering output from my clustering algorithm.
##
##
## -----------------------------------------------------------------------------

cluster.data <- read.csv("testClustering.csv")
summary(cluster.data)

cluster.data$x[is.nan(cluster.data$x)] <- NA
cluster.data$y[is.nan(cluster.data$y)] <- NA

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

yrange <- range(cluster.data$y, na.rm=TRUE)
xrange <- range(cluster.data$x, na.rm=TRUE)

# yrange <- range(cluster.data$y[cluster.data$isCentroid == 0])
# xrange <- range(cluster.data$x[cluster.data$isCentroid == 0])

plot(x=x, y=y, main="Data, Clustered", col=colors[1], pch=styles[1], ylim=yrange, xlim=xrange)
points(x=xcenter, y=ycenter, col=colors[1], pch="X")

centers <- list()
centers[[1]] <-  c(clusterID, xcenter, ycenter)

if (K > 1) {
  newcolors <- colors [-1]
  newclusters <- clusters[-1]
  newstyles <- styles[-1]
    
  for (i in c(1:(K-1))) {
    clusterID <- newclusters[i]
    clusterIndex <- cluster.data$cluster == clusterID
    x <- cluster.data$x[clusterIndex]
    y <- cluster.data$y[clusterIndex]

    clusterCenterIndex <- (cluster.data$cluster == clusterID) & (cluster.data$isCentroid == 1)
    xcenter <- cluster.data$x[clusterCenterIndex]
    ycenter <- cluster.data$y[clusterCenterIndex]

    centers[[i+1]] <- c(clusterID, xcenter, ycenter)
    
    points(x=x, y=y, col=newcolors[i], pch=newstyles[i])
    points(x=xcenter, y=ycenter, col=newcolors[i], pch="X")
    points(x=xcenter, y=ycenter, col=newcolors[i], pch="X")
  }
}


# i= 1
# clusterID <- clusters[i]
#     clusterCenterIndex <- (cluster.data$cluster == clusterID) & (cluster.data$isCentroid == 1)
#     xcenter <- cluster.data$x[clusterCenterIndex]
#     ycenter <- cluster.data$y[clusterCenterIndex]
# points(x=xcenter, y=ycenter, col="orange", pch="O")
# 
#     clusterCenterIndex <- (cluster.data$cluster == clusterID) & (cluster.data$isCentroid == 1)
#     xcenter <- cluster.data$x[clusterCenterIndex]
#     ycenter <- cluster.data$y[clusterCenterIndex]

centers
clusters