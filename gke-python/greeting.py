#!/usr/bin/env python3

import flask

app = flask.Flask(__name__)


@app.route('/', methods=['GET'])
def hello():
    return flask.jsonify({'greeting': 'hello'}), 200


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8899, debug=True)

