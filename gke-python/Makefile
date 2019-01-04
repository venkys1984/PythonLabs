.DEFAULT_GOAL := list

# execute as make <target> IMG=gcr.io/evd-ddl-us-services/greeting:v1 NAME=greeting MANIFEST=deployment.yaml

docker-build:
	sudo docker build --network host -t ${IMG} .

docker-run:
	sudo docker run --network host ${IMG}

push-image:
	gcloud auth print-access-token | sudo docker login -u oauth2accesstoken --password-stdin https://gcr.io && sudo docker push ${IMG}

create-manifest:
	kubectl run ${NAME} --image=${IMG} --port=8080 --dry-run=true --output=yaml --image-pull-policy='Always'

create-deployment:
	kubectl apply -f ${MANIFEST}

get-ip:
	kubectl expose deployment ${NAME} --type=LoadBalancer
	kubectl get services

delete-all:
	kubectl delete services/${NAME}
	kubectl delete deployment/${NAME}

get-all:
	kubectl get services/${NAME}
	kubectl get deployment/${NAME}
	kubectl get pods
	

list:
	@echo "docker-build docker-run push-image create-manifest delete-all get-all "

all: docker-build docker-run 
