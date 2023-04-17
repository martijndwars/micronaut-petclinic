import { Html, Head, Main, NextScript } from 'next/document'
import Link from 'next/link'

export default function Document() {
  return (
    <Html lang="en">
      <Head />
      <body>
        <div className="container">
          <ul className="menu">
            <li>
              <Link href="/">Home</Link>
            </li>
            <li >
              <Link href="/owners">Find Owners</Link>
            </li>
            <li>
              <Link href="/vets">Veterinarians</Link>
            </li>
          </ul>
          <Main />
        </div>
        <NextScript />
      </body>
    </Html>
  )
}
