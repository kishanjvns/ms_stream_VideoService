Tech: MiniIO for Object storage
docker run -p 9000:9000 -d -p 9001:9001 -e "MINIO_ROOT_USER=minio99" -e "MINIO_ROOT_PASSWORD=minio123" quay.io/minio/minio server /data --console-address ":9001"
Initially using H2 DB for local development
Using my BOM ms_stream_dependencies_bom
