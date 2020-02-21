import React, {useEffect} from 'react'
import {connect} from 'react-redux'
import {Typography, Button, Form, Input, message} from 'antd'

import {addSample} from '../../store/samples/actions'
import getSamples from '../../store/samples/selectors'

const {Title} = Typography

const SampleForm = props => {
    const {
        form: {getFieldDecorator, validateFields},
        samples
    } = props

    const handleAddSample = e => {
        e.preventDefault()
        validateFields((err, values) => {
            if (!err) {
                const {name} = values
                if (!samples.includes(name)) {
                    props.addSample(name)
                    message.success(`Text successfully added: ${name}.`)
                } else {
                    message.error(`Text already added: ${name}.`)
                }
            }
        })
    }

    return (
        <Form layout="inline" onSubmit={handleAddSample}>
            <Form.Item label="Name">
                {getFieldDecorator('name', {
                    rules: [
                        {
                            required: true
                        }
                    ]
                })(<Input autoFocus/>)}
            </Form.Item>
            <Form.Item>
                <Button type="primary" htmlType="submit">
                    Add
                </Button>
            </Form.Item>
        </Form>
    )
}

const Home = props => {
    const {samples} = props
    const WrappedSampleForm = Form.create({name: 'sample_form'})(
        connect(
            state => ({
                samples: getSamples(state)
            }),
            {addSample}
        )(SampleForm)
    )

    useEffect(() => {
        document.title = 'Home'
    }, [])

    return (
        <>
            <Title level={2}>Home</Title>
            <WrappedSampleForm/>
            <br/>
            <Title level={4}>Sample size: {samples.length}</Title>
            {samples.length > 0 && (
                <ul>
                    {samples.map(s => {
                        return <li key={s}>{s}</li>
                    })}
                </ul>
            )}
        </>
    )
}

export default connect(
    state => ({
        samples: getSamples(state)
    }),
    {addSample}
)(Home)
