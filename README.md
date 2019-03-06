## Installation Mysql Docker : 

Vous devez disposer du demon docker sur votre poste.
	 
```
docker run --restart=always --name=mysql -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password mysql:5.6
```

## Installation avec Kubernetes
Pour executer kubernetes sur un poste de developpement, je vous conseille d'installer minikube.

```
kubectl apply -f pod-mysql.yml
```
