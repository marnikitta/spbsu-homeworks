#Task 1

mean <- 0
sd <- 1
p.val <- rep(NA, 1000)

for(i in 1:1000) {
  data <- rnorm(100, mean = mean, sd = sd)
  res <- ks.test(data, "pnorm", mean = 0, sd = 1)
  p.val[i] <- res$p.value
}
summary(p.val)

plot(ecdf(p.val))


#Task 2
mean <- 0
sd <- 1
a <- 0.1
alpha <- 0.05

ns <- seq(1, 10000, 100)
h1 <- rep(0, length(ns))

for (j in 1:length(ns)) {
  for (i in 1:100) {
    data <- rnorm(ns[j], mean = a, sd = sd)
    res <- ks.test(data, "pnorm", mean = 0, sd = 1)
    if (res$p.value < alpha) {
      h1[j] <- h1[j] + 1
    }
  }
}

plot(ns, h1 / 100)


#Task 3
mean <- 0
sd <- 1
n <- 20
a <- 0.1

alphas <- seq(0.1, 1, 0.01)
h1 <- rep(0, length(alphas))

for (j in 1:length(ns)) {
  for (i in 1:100) {
    data <- rnorm(n, mean = a, sd = sd)
    res <- ks.test(data, "pnorm", mean = 0, sd = 1)
    if (res$p.value < alphas[j]) {
      h1[j] <- h1[j] + 1
    }
  }
}

plot(alphas, h1 / 100)
